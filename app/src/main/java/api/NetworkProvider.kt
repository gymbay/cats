package api

import api.interceptors.ApiKeyRequestInterceptor
import api.services.ImagesService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkProvider {

    private val retrofit: Retrofit

    init {
        retrofit = createProvider()
    }

    private fun createProvider() : Retrofit {
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

    @Singleton
    @Provides
    fun createImagesService(): ImagesService = retrofit.create(ImagesService::class.java)

}