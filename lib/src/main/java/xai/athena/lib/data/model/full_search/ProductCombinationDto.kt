package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class ProductCombinationDto(
    @SerializedName("color_shade") val colorShade: String? = null
)
