package xai.athena.lib.data.model.search

import com.google.gson.annotations.SerializedName

data class LandingModeUrlParamsDto(
    @SerializedName("q") val q: String? = null,
    @SerializedName("page") val page: String? = null,
    @SerializedName("product_list_mode") val productListMode: String? = null
)