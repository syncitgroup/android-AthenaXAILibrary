package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class PagerUrlParamsDto(
    @SerializedName("page") val page: String? = null
)
