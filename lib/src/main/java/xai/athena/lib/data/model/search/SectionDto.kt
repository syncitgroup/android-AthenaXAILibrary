package xai.athena.lib.data.model.search

import com.google.gson.annotations.SerializedName

data class SectionDto(
    @SerializedName("items") val items: List<SearchItemSectionDto>? = null,
    @SerializedName("title") val title: String? = null
)