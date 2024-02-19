package xai.athena.lib.data.model.search

import com.google.gson.annotations.SerializedName
import xai.athena.lib.data.model.full_search.BannerDto

data class BannersDto(
    @SerializedName("bottom") val bottomBanners: List<BannerDto>? = null,
    @SerializedName("top") val topBanners: List<BannerDto>? = null
)
