package xai.athena.lib.domain

import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri
import retrofit2.HttpException
import xai.athena.lib.data.model.SearchMessageDto
import xai.athena.lib.data.model.full_search.LandingSearchResultDto
import xai.athena.lib.data.model.search.SearchAutocompleteDto
import xai.athena.lib.data.model.search.SearchResultFirstClickDto
import xai.athena.lib.domain.request.AddToCartFromSearchBody
import xai.athena.lib.domain.request.ProductClickBody
import xai.athena.lib.domain.request.SearchBody
import xai.athena.lib.domain.request.SearchOrderBody
import kotlin.jvm.Throws

internal interface AthenaRepository {

    @Throws(HttpException::class, NotImplementedError::class, RuntimeException::class)
    suspend fun postVisualSearch(
        token: String,
        image: Bitmap,
        page: String,
        search: String,
        customer: String? = null,
        customerGroupId: String,
        filters: HashMap<String, String>? = null
    ): LandingSearchResultDto

    @Throws(HttpException::class, NotImplementedError::class, RuntimeException::class)
    suspend fun postVisualSearch(
        contentResolver: ContentResolver,
        token: String,
        image: Uri,
        page: String,
        search: String,
        customer: String? = null,
        customerGroupId: String,
        filters: HashMap<String, String>? = null
    ): LandingSearchResultDto

    @Throws(HttpException::class, NotImplementedError::class)
    suspend fun postFullSearch(
        token: String,
        query: String,
        page: String,
        search: String,
        customer: String,
        customerGroupId: String,
        filters: HashMap<String, String>?
    ): LandingSearchResultDto

    @Throws(HttpException::class, NotImplementedError::class)
    suspend fun postCategoryProducts(
        token: String,
        categoryId: String,
        categoryLevel: String,
        campaignId: String? = null,
        utmCampaign: String? = null,
        currentPage: Int,
        pageSize: Int? = null,
        customerGroupId: Int,
        filters: HashMap<String, String>?
    ): LandingSearchResultDto

    @Throws(HttpException::class, NotImplementedError::class)
    suspend fun postFirstClick(body: SearchBody): SearchResultFirstClickDto

    @Throws(HttpException::class, NotImplementedError::class)
    suspend fun postAutocomplete(body: SearchBody): SearchResultFirstClickDto

    @Throws(HttpException::class, NotImplementedError::class)
    suspend fun postSearchAutocomplete(
        token: String,
        query: String,
        page: String,
        search: String,
        customer: String,
        customerGroupId: String,
        filters: HashMap<String, String>?
    ): SearchAutocompleteDto

    @Throws(HttpException::class, NotImplementedError::class)
    suspend fun postOrder(body: SearchOrderBody): SearchMessageDto

    @Throws(HttpException::class, NotImplementedError::class)
    suspend fun postAddToCart(body: AddToCartFromSearchBody): SearchMessageDto

    @Throws(HttpException::class, NotImplementedError::class)
    suspend fun postProductClick(body: ProductClickBody): SearchMessageDto

}