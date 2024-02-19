package xai.athena.lib.domain.request

import com.google.gson.annotations.SerializedName

data class AddToCartFromSearchBody(
    @SerializedName("oId") val oId: String,
    @SerializedName("token") val token: String,
    @SerializedName("userToken") val userToken: String,
)
