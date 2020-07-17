package ru.gymbay.cats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import api.interceptors.ApiKeyRequestInterceptor
import api.models.images.CatImage
import api.services.ImagesService
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = ImagesService().createService()
        val response = service.getImages(mapOf("limit" to "10", "page" to "0"))
        response.enqueue(object : Callback<List<CatImage>> {
            override fun onFailure(call: Call<List<CatImage>>, t: Throwable) {
                Log.d("RETROFIT", "Error")
            }

            override fun onResponse(
                call: Call<List<CatImage>>,
                response: Response<List<CatImage>>
            ) {
                Log.d("RETROFIT", response.body()?.size.toString())
            }
        })
    }

}