package xai.athena.lib.data.model.search

import com.google.gson.annotations.SerializedName
import xai.athena.lib.data.model.full_search.LandingProductsDto

data class SearchAutocompleteResponseDto(

    @SerializedName("query") val query: String? = null,
    @SerializedName("used_suggestions") val usedSuggestions: Boolean? = null,
    @SerializedName("current_url") val currentUrl: String? = null,
    @SerializedName("current_path") val currentPath: String? = null,
    @SerializedName("true_autocomplete_keyword") val trueAutocompleteKeyword: String? = null,
    @SerializedName("products") val products: LandingProductsDto? = null,
    @SerializedName("banners") val banners: BannersDto? = null,
    @SerializedName("sections") val sections: ResultsDto? = null
)
