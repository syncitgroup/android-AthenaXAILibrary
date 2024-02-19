package xai.athena.lib.domain.request

import com.google.gson.annotations.SerializedName

data class SearchBody(
    @SerializedName("q") val q: String,
    @SerializedName("in_results_array") val inResultsArray: Boolean,
    @SerializedName("token") val token: String,
    @SerializedName("customer_group_id") val customerGroupId: String
)