package xai.athena.lib.data.utils

import okhttp3.Interceptor
import okhttp3.Response

internal class AthenaInterceptor(
    private val token: String
) : Interceptor {

    /**
     * This is customer interceptor and we have to provide customer token through header
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request =
            request.newBuilder()
                .addHeader("Accept", "Application/JSON")
                .addHeader(
                    name = "Authorization",
                    value = "Bearer $token"
                )
                .addHeader(
                    name = "Client-Source",
                    value = "Mobile App"
                )
                .addHeader(
                    name = "Device",
                    value = "Mobile"
                )
                .addHeader(
                    name = "Platform",
                    value = "Android"
                )
                .build()

        return chain.proceed(request)
    }
}