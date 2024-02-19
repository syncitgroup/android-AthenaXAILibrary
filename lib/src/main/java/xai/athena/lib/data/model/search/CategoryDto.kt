package xai.athena.lib.data.model.search

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("items") val items: List<SearchItemCategoryDto>? = null,
    @SerializedName("title") val title: String? = null
)