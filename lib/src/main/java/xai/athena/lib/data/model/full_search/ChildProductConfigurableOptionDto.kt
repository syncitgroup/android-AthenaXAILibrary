package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class ChildProductConfigurableOptionDto(
    @SerializedName("type") val type: String? = null,
    @SerializedName("value") val value: String? = null,
)
