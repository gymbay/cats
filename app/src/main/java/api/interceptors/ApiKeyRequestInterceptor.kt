package api.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyRequestInterceptor: Interceptor {

    private val apiKey = "bfe38157-77f1-417e-b4d6-3307a36e1c9e"

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .header("x-api-key", apiKey)
            .method(originalRequest.method, originalRequest.body)
            .build()

        return chain.proceed(newRequest)
    }

}