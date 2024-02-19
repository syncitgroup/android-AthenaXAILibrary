package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class FiltersArrayDto(
    @SerializedName("option_value") val optionValue: String? = null,
    @SerializedName("option_key") val optionKey: String? = null,
    @SerializedName("option_label") val optionLabel: String? = null,
    @SerializedName("selected") val selected: Boolean? = null,
    @SerializedName("count") val count: Int? = null,
    @SerializedName("type_id") val typeId: String? = null,
    @SerializedName("hax_code") val haxCode: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("url_path") val urlPath: String? = null,
    @SerializedName("url_params") val urlParams: Map<String, String>? = null,
)
