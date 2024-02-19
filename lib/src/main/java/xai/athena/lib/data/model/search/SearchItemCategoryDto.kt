package xai.athena.lib.data.model.search

import com.google.gson.annotations.SerializedName

data class SearchItemCategoryDto(
    @SerializedName("image") val image: String? = null,
    @SerializedName("highlighted_name") val highlightedName: String? = null,
    @SerializedName("link") val link: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("id") val id: Int? = null
)