package xai.athena.lib.data.model.full_search

import com.google.gson.annotations.SerializedName

data class Amounts(
    @SerializedName("current_page") val currentPage: Int? = null,
    @SerializedName("last_page") val lastPage: Int? = null,
    @SerializedName("per_page") val perPage: Int? = null,
    @SerializedName("total") val total: Int? = null,
    @SerializedName("from") val from: Int? = null,
    @SerializedName("next_page") val nextPage: Int? = null,
    @SerializedName("prev_page") val prevPage: Int? = null,
    @SerializedName("to") val to: Int? = null
)