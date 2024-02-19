package xai.athena.lib.data.model.search

import com.google.gson.annotations.SerializedName
import xai.athena.lib.data.model.PriceObject
import xai.athena.lib.data.model.ProductBrandDto

data class SearchItemProductDto(
    @SerializedName("image") val image: String? = null,
    @SerializedName("highlighted_name") val highlightedName: String? = null,
    @SerializedName("full_image_path") val fullImagePath: String? = null,
    @SerializedName("link") val link: String? = null,
    @SerializedName("discount_percentage") val discountPercentage: Int? = null,
    @SerializedName("product_type_id") val productTypeId: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("short_description") val shortDescription: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("sku") val sku: String? = null,
    @SerializedName("brand") val brand: ProductBrandDto? = null,
    @SerializedName("category_names") val itemCategory: List<String>? = null,
    @SerializedName("gallery_images") val galleryImages: List<String>? = null,
    @SerializedName("price") val price: PriceObject? = null,
)