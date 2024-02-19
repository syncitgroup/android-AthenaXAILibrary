package xai.athena.lib.data.model

import com.google.gson.annotations.SerializedName

data class ProductBrandDto(
    @SerializedName("option_id") val optionId: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("label") val label: String? = null,
    @SerializedName("attribute_code") val attributeCode: String? = null,
)