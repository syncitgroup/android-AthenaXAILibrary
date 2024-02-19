package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class SorterDto(
    @SerializedName("option_key") val optionKey: String? = null,
    @SerializedName("option_value") val optionValue: String? = null,
    @SerializedName("option_label") val optionLabel: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("url_path") val urlPath: String? = null,
    @SerializedName("selected") val selected: Boolean? = null,
    @SerializedName("url_params") val urlParams: SorterUrlParamsDto? = null,
)
