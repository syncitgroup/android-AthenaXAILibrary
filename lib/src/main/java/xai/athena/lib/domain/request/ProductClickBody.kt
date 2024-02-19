package xai.athena.lib.domain.request

import com.google.gson.annotations.SerializedName

/**
 * productId - unique id for product
 * type - search or autocomplete
 * searchKeywords - search keywords before click on products
 * customer - customer email
 */
data class ProductClickBody(
    @SerializedName("token") val token: String,
    @SerializedName("productId") val productId: String,
    @SerializedName("type") val type: String,
    @SerializedName("searchKeywords") val searchKeywords: String,
    @SerializedName("customer") val customer: String,
)