package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class LandingSearchResultDto(
    @SerializedName("data") val data: LandingDataDto? = null,
    @SerializedName("position") val position: Int? = null
)