package xai.athena.lib.domain.request

import com.google.gson.annotations.SerializedName

data class SearchOrderBody(
    @SerializedName("token") val token: String,
    @SerializedName("userToken") val userToken: String,
    @SerializedName("order") val order: SearchOrder,
)

data class SearchOrder(
    @SerializedName("quote_id") val quoteId: Int,
    @SerializedName("increment_id") val incrementId: String,
    @SerializedName("store_id") val storeId: Int,
    @SerializedName("status") val status: String,
    @SerializedName("order_currency_code") val orderCurrencyCode: String,
    @SerializedName("base_grand_total") val baseGrandTotal: Float,
    @SerializedName("subtotal_price") val subtotalPrice: Float,
    @SerializedName("tax_price") val taxPrice: Float,
    @SerializedName("discount_price") val discountPrice: Float,
    @SerializedName("shipping_price") val shippingPrice: Float,
    @SerializedName("items") val items: List<SearchOrderItem>
)

data class SearchOrderItem(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
    @SerializedName("sku") val sku: String,
    @SerializedName("type") val type: String,
    @SerializedName("qty") val qty: Int,
    @SerializedName("price_incl_tax") val priceInclTax: Float,
    @SerializedName("price_with_qty_incl_tax") val priceWithQtyInclTax: Float,
    @SerializedName("price") val price: Float,
    @SerializedName("row_total") val rowTotal: Float
)