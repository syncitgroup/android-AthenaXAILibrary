package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName
import xai.athena.lib.data.model.search.LandingModeDto

data class LandingDataDto(
    @SerializedName("products") val products: LandingProductsDto? = null,
    @SerializedName("query") val query: String? = null,
    @SerializedName("suggested_text") val suggestedText: String? = null,
    @SerializedName("used_suggestions") val usedSuggestions: Boolean? = null,
    @SerializedName("current_url") val currentUrl: String? = null,
    @SerializedName("current_path") val currentPath: String? = null,
    @SerializedName("blog") val blog: LandingProductsDto? = null,
    @SerializedName("modes") val modes: List<LandingModeDto>? = null,
    @SerializedName("banners") val banners: List<BannerDto>? = null,
    @SerializedName("image_cache") internal val imageCache: String? = null
)