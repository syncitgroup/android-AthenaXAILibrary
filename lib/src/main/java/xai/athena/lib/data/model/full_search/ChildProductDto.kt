package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class ChildProductDto(
    @SerializedName("image") val image: String? = null,
    @SerializedName("stock_status") val stockStatus: Boolean? = null,
    @SerializedName("color_shade") val colorShade: String? = null,
    @SerializedName("hover_image") val hoverImage: String? = null,
    @SerializedName("entity_id") val entityId: String? = null,
    @SerializedName("sku") val sku: String? = null,
    @SerializedName("configurable_options") val configurableOptions: List<ChildProductConfigurableOptionDto>? = null,
)
