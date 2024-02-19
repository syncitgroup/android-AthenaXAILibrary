package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class CollectionPriceDto(
    @SerializedName("lowest_price") val lowestPrice: Float? = null,
    @SerializedName("highest_price") val highestPrice: Float? = null,
    @SerializedName("lowest_price_with_filters") val lowestPriceWithFilters: Float? = null,
    @SerializedName("highest_price_with_filters") val highestPriceWithFilters: Float? = null,
)