package xai.athena.lib.data.model.search

import com.google.gson.annotations.SerializedName

data class PdfDocumentItemDto(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("highlighted_name") val highlightedName: String? = null,
    @SerializedName("link") val link: String? = null,
    @SerializedName("author_name") val authorName: String? = null,
    @SerializedName("author_url") val authorUrl: String? = null,
    @SerializedName("attachment") val attachment: PdfAttachmentDto? = null
)
