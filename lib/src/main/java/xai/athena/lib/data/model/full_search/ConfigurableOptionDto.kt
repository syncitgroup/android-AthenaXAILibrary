package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class ConfigurableOptionDto(
    @SerializedName("attribute_id") val attributeId: Int? = null,
    @SerializedName("options") val options: List<OptionDto>? = null,
    @SerializedName("attribute_code") val attributeCode: String? = null
)
