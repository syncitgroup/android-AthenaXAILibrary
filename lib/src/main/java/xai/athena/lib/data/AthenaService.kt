package xai.athena.lib.data

import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.util.LruCache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xai.athena.lib.data.api.AthenaApi
import xai.athena.lib.data.model.SearchMessageDto
import xai.athena.lib.data.model.full_search.LandingSearchResultDto
import xai.athena.lib.data.model.search.SearchAutocompleteDto
import xai.athena.lib.data.model.search.SearchResultFirstClickDto
import xai.athena.lib.data.utils.AthenaInterceptor
import xai.athena.lib.domain.AthenaRepository
import xai.athena.lib.domain.request.AddToCartFromSearchBody
import xai.athena.lib.domain.request.ProductClickBody
import xai.athena.lib.domain.request.SearchBody
import xai.athena.lib.domain.request.SearchOrderBody
import xai.athena.lib.image.BackgroundRemover
import xai.athena.lib.image.compressBitmap
import xai.athena.lib.image.convertBitmapToBase64
import xai.athena.lib.image.getBitmapFromUri
import xai.athena.lib.image.getImageExtension
import xai.athena.lib.image.getResizedBitmap
import java.util.concurrent.TimeUnit
import kotlin.jvm.Throws

/**
 * Provide Athena API, should be declared as singleton
 */
class AthenaService: AthenaRepository {

    companion object {
        private const val MAXIMUM_FILE_SIZE_IN_BYTES = 31_457_280
        private const val IMAGE_RESIZE_WIDTH = 1000
        private const val API_NOT_IMPLEMENTED_MESSAGE = "An Api Service is not implemented. Please call setup() function first."
        private const val INVALID_FORMAT_EXCEPTION = "Supported image formats are jpg, jpeg, png, and heic."
        private const val FILE_TOO_LARGE_EXCEPTION = "Selected image is too large. Limit: $MAXIMUM_FILE_SIZE_IN_BYTES bytes."
        private const val TAG = "AthenaService"

        private const val IS_BACKGROUND_REMOVAL_ENABLED = false // Determine if the background of the image will be removed by MLKit or not
    }

    // Image caching objects
    private val uriKeyCache: LruCache<Uri, String> = LruCache(1)
    private val bitmapKeyCache: LruCache<Bitmap, String> = LruCache(1)

    private var api: AthenaApi? = null
    private var retrofit: Retrofit? = null

    private var loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private var okHttpClient: OkHttpClient.Builder =
        OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

    /**
     * Provide custom interceptors to Http client
     * Must be called before setup()
     */
    fun addInterceptor(interceptor: Interceptor) {
        if (!okHttpClient.interceptors().contains(interceptor)) {
            okHttpClient.addInterceptor(interceptor)
        }
    }

    /**
     * Setup athena with token and baseUrl
     */
    fun setup(accessToken: String, baseUrl: String) {
        setupClient(accessToken, baseUrl)
    }

    private fun setupClient(token: String, baseUrl: String) {
        val interceptor = AthenaInterceptor(token = token)

        if (!okHttpClient.interceptors().contains(interceptor)) {
            okHttpClient.addInterceptor(interceptor)
        }

        retrofit =
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()

        api = retrofit?.create(AthenaApi::class.java)
    }

    @Throws(HttpException::class, NotImplementedError::class, RuntimeException::class, IllegalArgumentException::class)
    override suspend fun postVisualSearch(
        token: String,
        image: Bitmap,
        page: String,
        search: String,
        customer: String?,
        customerGroupId: String,
        filters: HashMap<String, String>?
    ): LandingSearchResultDto {
        val cachedKey = bitmapKeyCache.get(image)
        if (cachedKey != null) {
            Log.e(TAG, "Cached Image exists, using cached Base64...")
            return processVisualSearch(
                token = token,
                imageBase64OrKey = cachedKey,
                originalBitmap = image,
                page = page,
                search = search,
                customer = customer,
                customerGroupId = customerGroupId,
                filters = filters
            )
        }

        Log.e(TAG, "Image processing start...")
        var bitmap = getResizedBitmap(image, IMAGE_RESIZE_WIDTH)
        bitmap = compressBitmap(bitmap)
        Log.e(TAG, "Image is resized and compressed...")

        // Remove background with MLKit
        if (IS_BACKGROUND_REMOVAL_ENABLED) {
            Log.e(TAG, "Background removing start...")
            bitmap = BackgroundRemover.bitmapForProcessing(bitmap, trimEmptyPart = false)
            Log.e(TAG, "Image background is removed, converting to Base64...")
        }

        if (bitmap.byteCount > MAXIMUM_FILE_SIZE_IN_BYTES) {
            throw IllegalArgumentException(FILE_TOO_LARGE_EXCEPTION)
        }

        val imageBase64 = convertBitmapToBase64(bitmap)
        Log.e(TAG, "Base64: $imageBase64")

        return processVisualSearch(
            token = token,
            imageBase64OrKey = imageBase64,
            originalBitmap = image,
            page = page,
            search = search,
            customer = customer,
            customerGroupId = customerGroupId,
            filters = filters
        )
    }

    @Throws(HttpException::class, NotImplementedError::class, RuntimeException::class, IllegalArgumentException::class)
    override suspend fun postVisualSearch(
        contentResolver: ContentResolver,
        token: String,
        image: Uri,
        page: String,
        search: String,
        customer: String?,
        customerGroupId: String,
        filters: HashMap<String, String>?
    ): LandingSearchResultDto {

        val cachedKey = uriKeyCache.get(image)
        if (cachedKey != null) {
            Log.e(TAG, "Cached Key for Image exists, using cached key...")
            return processVisualSearch(
                token = token,
                imageBase64OrKey = cachedKey,
                originalUri = image,
                page = page,
                search = search,
                customer = customer,
                customerGroupId = customerGroupId,
                filters = filters
            )
        }

        val fileExtension = getImageExtension(contentResolver, image).lowercase()
        if (!isValidExtension(fileExtension)) {
            throw IllegalArgumentException(INVALID_FORMAT_EXCEPTION)
        }

        Log.e(TAG, "Image processing start...")
        var bitmap = getBitmapFromUri(contentResolver = contentResolver, imageUri = image)
        Log.e(TAG, "Bitmap size before processing: ${(bitmap.byteCount * 1.0f) / 1024} KB")
        bitmap = getResizedBitmap(bitmap, IMAGE_RESIZE_WIDTH)
        bitmap = compressBitmap(bitmap)
        Log.e(TAG, "Image is resized and compressed...")

        // Remove background with MLKit
        if (IS_BACKGROUND_REMOVAL_ENABLED) {
            Log.e(TAG, "Background removing start...")
            bitmap = BackgroundRemover.bitmapForProcessing(bitmap, trimEmptyPart = false)
            Log.e(TAG, "Image background is removed, converting to Base64... Bitmap size: ${(bitmap.byteCount * 1.0f) / 1024} KB")
        }

        if (bitmap.byteCount > MAXIMUM_FILE_SIZE_IN_BYTES) {
            throw IllegalArgumentException(FILE_TOO_LARGE_EXCEPTION)
        }

        val imageBase64 = convertBitmapToBase64(bitmap)
        Log.e(TAG, "Base64: $imageBase64")

        return processVisualSearch(
            token = token,
            imageBase64OrKey = imageBase64,
            originalUri = image,
            page = page,
            search = search,
            customer = customer,
            customerGroupId = customerGroupId,
            filters = filters
        )
    }

    override suspend fun postFullSearch(
        token: String,
        query: String,
        page: String,
        search: String,
        customer: String,
        customerGroupId: String,
        filters: HashMap<String, String>?
    ): LandingSearchResultDto {
        val requestBody: HashMap<String, String> = hashMapOf()
        requestBody["token"] = token
        requestBody["q"] = query
        requestBody["page"] = page
        requestBody["search"] = search
        requestBody["customer"] = customer
        requestBody["customer_group_id"] = customerGroupId

        if (filters != null) {
            requestBody.putAll(filters)
        }

        try {
            api?.let {
                return it.postFullSearch(requestBody)
            }
            throw NotImplementedError(API_NOT_IMPLEMENTED_MESSAGE)
        } catch (e: HttpException) {
            throw e
        }
    }

    override suspend fun postCategoryProducts(
        token: String,
        categoryId: String,
        categoryLevel: String,
        campaignId: String?,
        utmCampaign: String?,
        currentPage: Int,
        pageSize: Int?,
        customerGroupId: Int,
        filters: HashMap<String, String>?
    ): LandingSearchResultDto {
        val requestBody: HashMap<String, String> = hashMapOf()
        requestBody["token"] = token
        requestBody["category"] = categoryId
        requestBody["level"] = categoryLevel

        // Send campaign_id or utm_campaign only if there is NO available filters
        if (filters.isNullOrEmpty()) {
            if (campaignId != null && currentPage < 2) requestBody["campaign_id"] = campaignId
            if (campaignId == null && utmCampaign != null) requestBody["utm_campaign"] = utmCampaign
        }

        if (pageSize != null) requestBody["product_list_limit"] = pageSize.toString()

        requestBody["page"] = currentPage.toString()
        requestBody["customer_group_id"] = customerGroupId.toString()
        if (filters != null) requestBody.putAll(filters)

        try {
            api?.let {
                return it.postCategoryProducts(requestBody)
            }
            throw NotImplementedError(API_NOT_IMPLEMENTED_MESSAGE)
        } catch (e: HttpException) {
            throw e
        }
    }

    @Throws(HttpException::class, NotImplementedError::class)
    override suspend fun postFirstClick(body: SearchBody): SearchResultFirstClickDto {
        try {
            api?.let {
                return it.postFirstClick(body)
            }
            throw NotImplementedError(API_NOT_IMPLEMENTED_MESSAGE)
        } catch (e: HttpException) {
            throw e
        }
    }

    @Throws(HttpException::class, NotImplementedError::class)
    override suspend fun postAutocomplete(body: SearchBody): SearchResultFirstClickDto {
        try {
            api?.let {
                return it.postAutocomplete(body)
            }
            throw NotImplementedError(API_NOT_IMPLEMENTED_MESSAGE)
        } catch (e: HttpException) {
            throw e
        }
    }

    override suspend fun postSearchAutocomplete(
        token: String,
        query: String,
        page: String,
        search: String,
        customer: String,
        customerGroupId: String,
        filters: HashMap<String, String>?
    ): SearchAutocompleteDto {
        val requestBody: HashMap<String, String> = hashMapOf()
        requestBody["token"] = token
        requestBody["q"] = query
        requestBody["page"] = page
        requestBody["search"] = search
        requestBody["customer"] = customer
        requestBody["customer_group_id"] = customerGroupId

        if (filters != null) {
            requestBody.putAll(filters)
        }

        try {
            api?.let {
                return it.postSearchAutocomplete(requestBody)
            }
            throw NotImplementedError(API_NOT_IMPLEMENTED_MESSAGE)
        } catch (e: HttpException) {
            throw e
        }
    }

    override suspend fun postOrder(body: SearchOrderBody): SearchMessageDto {
        try {
            api?.let {
                return it.postOrder(body)
            }
            throw NotImplementedError(API_NOT_IMPLEMENTED_MESSAGE)
        } catch (e: HttpException) {
            throw e
        }
    }

    override suspend fun postAddToCart(body: AddToCartFromSearchBody): SearchMessageDto {
        try {
            api?.let {
                return it.postAddToCart(body)
            }
            throw NotImplementedError(API_NOT_IMPLEMENTED_MESSAGE)
        } catch (e: HttpException) {
            throw e
        }
    }

    override suspend fun postProductClick(body: ProductClickBody): SearchMessageDto {
        try {
            api?.let {
                return it.postProductClick(body)
            }
            throw NotImplementedError(API_NOT_IMPLEMENTED_MESSAGE)
        } catch (e: HttpException) {
            throw e
        }
    }

    private suspend fun processVisualSearch(
        token: String,
        imageBase64OrKey: String,
        originalUri: Uri? = null,
        originalBitmap: Bitmap? = null,
        page: String,
        search: String,
        customer: String?,
        customerGroupId: String,
        filters: HashMap<String, String>?
    ): LandingSearchResultDto {
        val requestBody: HashMap<String, String> = hashMapOf()
        requestBody["token"] = token
        requestBody["image"] = imageBase64OrKey
        requestBody["page"] = page
        requestBody["search"] = search
        if (customer != null) requestBody["customer"] = customer
        requestBody["customer_group_id"] = customerGroupId

        if (filters != null) {
            requestBody.putAll(filters)
        }

        try {
            api?.let {
                val response = it.postVisualSearch(requestBody)

                // Caching
                if (originalUri != null) {
                    uriKeyCache.evictAll()
                    uriKeyCache.put(originalUri, response.data?.imageCache)
                }
                else if (originalBitmap != null) {
                    bitmapKeyCache.evictAll()
                    bitmapKeyCache.put(originalBitmap, response.data?.imageCache)
                }

                return it.postVisualSearch(requestBody)
            }
            throw NotImplementedError(API_NOT_IMPLEMENTED_MESSAGE)
        } catch (e: HttpException) {
            throw e
        }
    }

    private fun isValidExtension(fileExtension: String): Boolean {
        return fileExtension == "jpg" || fileExtension == "jpeg" || fileExtension == "png" || fileExtension == "heic"
    }

}