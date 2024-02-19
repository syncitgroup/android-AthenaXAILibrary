package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class AttributeDto(
    @SerializedName("parent") val parent: String? = null,
    @SerializedName("seo_url") val seoUrl: String? = null,
    @SerializedName("hash_code") val hashCode: String? = null,
    @SerializedName("original_value") val originalValue: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("label") val label: String? = null,
    @SerializedName("value") val value: String? = null,
    @SerializedName("original_parent") val originalParent: String? = null
)
