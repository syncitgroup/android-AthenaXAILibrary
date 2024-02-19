package xai.athena.lib.data.model

import com.google.gson.annotations.SerializedName

data class SearchMessageDto(
    @SerializedName("message") val message: String? = null
)
