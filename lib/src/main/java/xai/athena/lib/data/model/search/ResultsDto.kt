package xai.athena.lib.data.model.search

import com.google.gson.annotations.SerializedName

data class ResultsDto(
    @SerializedName("product") val product: ProductDto? = null,
    @SerializedName("section_a") val sectionA: SectionDto? = null,
    @SerializedName("section_b") val sectionB: SectionDto? = null,
    @SerializedName("popular") val popular: SectionDto? = null,
    @SerializedName("category") val category: CategoryDto? = null,
    @SerializedName("pdf_documents") val pdfDocuments: PdfDocumentsDto? = null
)