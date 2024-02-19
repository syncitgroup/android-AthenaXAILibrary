package xai.athena.lib.data.model.search

import com.google.gson.annotations.SerializedName

data class DataObjectDto(
    @SerializedName("results") val results: ResultsDto? = null,
    @SerializedName("true_autocomplete_keyword") val trueAutocompleteKeyword: String? = null,
    @SerializedName("banners") val banners: BannersDto? = null
)