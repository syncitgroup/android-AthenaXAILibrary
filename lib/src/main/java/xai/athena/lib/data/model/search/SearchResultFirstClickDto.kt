package xai.athena.lib.data.model.search

import com.google.gson.annotations.SerializedName

data class SearchResultFirstClickDto(
    @SerializedName("data") val data: DataObjectDto? = null
)