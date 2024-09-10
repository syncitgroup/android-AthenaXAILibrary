package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class LandingProductsDto(
    @SerializedName("results") val results: List<LandingProductDto>? = null,
    @SerializedName("amounts") val amounts: Amounts? = null,
    @SerializedName("filters") val filters: List<SearchFilterDto>? = null,
    @SerializedName("active_filters") val activeFilters: List<ActiveFilterDto>? = null,
    @SerializedName("collection_price") val collectionPrice: CollectionPriceDto? = null,
    @SerializedName("sorters") val sorters: List<SorterDto>? = null,
    @SerializedName("orders") val orders: List<OrderDto>? = null,
    @SerializedName("pager") val pager: List<PagerDto>? = null,
    //@SerializedName("visual_filter") val visualFilter: List<SearchFilterDto>? = null,
)