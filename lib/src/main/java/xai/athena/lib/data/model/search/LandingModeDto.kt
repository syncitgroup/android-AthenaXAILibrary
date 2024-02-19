package xai.athena.lib.data.model.search

import com.google.gson.annotations.SerializedName

data class LandingModeDto(
    @SerializedName("name") val name: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("option_key") val optionKey: String? = null,
    @SerializedName("option_value") val optionValue: String? = null,
    @SerializedName("option_label") val optionLabel: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("url_path") val urlPath: String? = null,
    @SerializedName("selected") val selected: Boolean? = null,
    @SerializedName("url_params") val urlParams: LandingModeUrlParamsDto? = null
)