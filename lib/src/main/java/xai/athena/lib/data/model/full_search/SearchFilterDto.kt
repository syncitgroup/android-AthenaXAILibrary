package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class SearchFilterDto(
    @SerializedName("title") val title: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("array") val array: List<FiltersArrayDto>? = null
)