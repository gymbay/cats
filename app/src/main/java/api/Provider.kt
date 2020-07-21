package api

import api.interceptors.ApiKeyRequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Provider {

    fun createProvider() : Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(ApiKeyRequestInterceptor())
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

}