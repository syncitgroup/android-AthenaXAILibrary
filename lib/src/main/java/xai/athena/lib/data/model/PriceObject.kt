package xai.athena.lib.data.model

import com.google.gson.annotations.SerializedName
import xai.athena.lib.data.model.full_search.CustomGroupPricesDto

data class PriceObject(
    @SerializedName("discount_percentage") val discountPercentage: Int? = null,
    @SerializedName("regular_price") val regularPrice: String? = null,
    @SerializedName("regular_price_with_currency") val regularPriceWithCurrency: String? = null,
    @SerializedName("special_price") val specialPrice: String? = null,
    @SerializedName("special_price_with_currency") val specialPriceWithCurrency: String? = null,
    @SerializedName("loyalty_price") val loyaltyPrice: String? = null,
    @SerializedName("loyalty_price_with_currency") val loyaltyPriceWithCurrency: String? = null,
    @SerializedName("best_month_price") val bestMonthPrice: String? = null,
    @SerializedName("best_month_price_with_currency") val bestMonthPriceWithCurrency: String? = null,
    @SerializedName("custom_field_prices") val customFieldPrices: CustomGroupPricesDto? = null,
)