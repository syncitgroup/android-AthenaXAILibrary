package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class OptionDto(
    @SerializedName("option_label") val optionLabel: String? = null,
    @SerializedName("hash_code") val hashCode: String? = null,
    @SerializedName("option_type") val optionType: String? = null,
    @SerializedName("option_id") val optionId: String? = null,
    @SerializedName("seo_value") val seoValue: String? = null
)
