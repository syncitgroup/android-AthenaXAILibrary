package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class SorterUrlParamsDto(
    @SerializedName("page") val page: String? = null,
    @SerializedName("product_list_order") val productListOrder: String? = null
)
