package xai.athena.lib.data.model.search

import com.google.gson.annotations.SerializedName

data class PdfDocumentsDto(
    @SerializedName("items") val items: List<PdfDocumentItemDto>? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("position") val position: Int? = null
)