package xai.athena.lib.data.model.search

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("items") val items: List<SearchItemProductDto>? = null,
    @SerializedName("title") val title: String? = null
)