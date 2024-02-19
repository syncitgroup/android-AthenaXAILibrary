package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName
import xai.athena.lib.data.model.PriceObject
import xai.athena.lib.data.model.ProductBrandDto

data class LandingProductDto(
    @SerializedName("short_description") val shortDescription: String? = null,
    @SerializedName("link") val link: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("created_at") val createdAt: String? = null,
    @SerializedName("updated_at") val updatedAt: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("availability") val availability: Int? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("views") val views: Int? = null,
    @SerializedName("sku") val sku: String? = null,
    @SerializedName("meta_title") val metaTitle: String? = null,
    @SerializedName("full_image_path") val fullImagePath: String? = null,
    @SerializedName("hover_image") val hoverImage: String? = null,
    @SerializedName("product_type_id") val productTypeId: String? = null,
    @SerializedName("meta_description") val metaDescription: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("price") val price: PriceObject? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("brand") val brand: ProductBrandDto? = null,
    @SerializedName("category_names") val categoryNames: List<String>? = null,
    @SerializedName("category_ids") val categoryIds: List<Int>? = null,
    @SerializedName("gallery_images") val galleryImages: List<String>? = null,
    @SerializedName("configurable_options") val configurableOptions: List<ConfigurableOptionDto>? = null,
    @SerializedName("attributes") val attributes: List<AttributeDto>? = null,
    @SerializedName("product_combinations") val productCombinations: List<ProductCombinationDto>? = null,
    @SerializedName("child_products") val childProducts: List<ChildProductDto>? = null
)