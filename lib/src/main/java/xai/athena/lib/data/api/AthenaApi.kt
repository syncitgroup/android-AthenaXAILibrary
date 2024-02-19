package xai.athena.lib.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import xai.athena.lib.data.model.SearchMessageDto
import xai.athena.lib.data.model.full_search.LandingSearchResultDto
import xai.athena.lib.data.model.search.SearchAutocompleteDto
import xai.athena.lib.data.model.search.SearchResultFirstClickDto
import xai.athena.lib.domain.request.AddToCartFromSearchBody
import xai.athena.lib.domain.request.ProductClickBody
import xai.athena.lib.domain.request.SearchBody
import xai.athena.lib.domain.request.SearchOrderBody

internal interface AthenaApi {

    @POST("api/v2/visual-similarity-search")
    suspend fun postVisualSearch(@Body req: HashMap<String,String>): LandingSearchResultDto

    @POST("api/v2/search/data")
    suspend fun postFullSearch(@Body req: HashMap<String,String>): LandingSearchResultDto

    @POST("api/v2/category/data")
    suspend fun postCategoryProducts(@Body req: HashMap<String,String>): LandingSearchResultDto

    @POST("api/v2/autocomplete/first-click")
    suspend fun postFirstClick(@Body body: SearchBody): SearchResultFirstClickDto

    @POST("api/v2/autocomplete")
    suspend fun postAutocomplete(@Body body: SearchBody): SearchResultFirstClickDto

    @POST("api/v2/search/autocomplete")
    suspend fun postSearchAutocomplete(@Body req: HashMap<String,String>): SearchAutocompleteDto

    @POST("api/v2/conversion/order")
    suspend fun postOrder(@Body body: SearchOrderBody): SearchMessageDto

    @POST("api/v2/conversion/cart")
    suspend fun postAddToCart(@Body body: AddToCartFromSearchBody): SearchMessageDto

    @POST("api/v2/product-click")
    suspend fun postProductClick(@Body body: ProductClickBody): SearchMessageDto
}