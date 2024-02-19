package xai.athena.lib.data.model.search

import com.google.gson.annotations.SerializedName

data class SearchAutocompleteDto(
    @SerializedName("data") val data: SearchAutocompleteResponseDto? = null
)