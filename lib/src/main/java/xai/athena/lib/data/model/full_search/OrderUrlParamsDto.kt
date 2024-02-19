package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class OrderUrlParamsDto(
    @SerializedName("product_list_limit") val productListLimit: String? = null
)
