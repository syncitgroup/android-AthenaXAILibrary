package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class ActiveFilterDto(
    @SerializedName("name") val name: String? = null,
    @SerializedName("label") val label: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("id") val id: String? = null,
)