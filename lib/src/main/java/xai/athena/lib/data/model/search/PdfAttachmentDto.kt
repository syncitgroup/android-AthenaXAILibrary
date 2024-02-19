package xai.athena.lib.data.model.search

import com.google.gson.annotations.SerializedName

data class PdfAttachmentDto(
    @SerializedName("date") val date: String? = null,
    @SerializedName("keywords") val keywords: String? = null,
    @SerializedName("content_type") val contentType: String? = null,
    @SerializedName("author") val author: String? = null,
    @SerializedName("language") val language: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("content") val content: String? = null,
    @SerializedName("content_length") val contentLength: Int? = null
)
