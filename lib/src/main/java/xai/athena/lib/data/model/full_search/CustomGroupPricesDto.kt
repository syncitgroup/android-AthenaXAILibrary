package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class CustomGroupPricesDto(
    @SerializedName("loyalty_price") val loyaltyPrice: Double? = null,
    @SerializedName("special_price") val specialPrice: Double? = null,
    @SerializedName("regular_price") val regularPrice: Double? = null,
    @SerializedName("best_month_price") val bestMonthPrice: Double? = null,
)