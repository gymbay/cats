package ru.gymbay.cats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import api.services.ImagesService
import di.MyApplication
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var imagesService: ImagesService

    override fun onCreate(savedInstanceState: Bundle?) {
        // Make Dagger instantiate @Inject fields
        (application as MyApplication).applicationComponent.inject(this)
        // Now imagesService is available

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val service = ImagesService().createService()
        // Example 3
        val service = imagesService.createService()

        // Example 1
//        val response = service.getImages(parameters = mapOf("limit" to "10", "page" to "0"))
//        response.enqueue(object : Callback<List<CatImageResponse>> {
//            override fun onFailure(call: Call<List<CatImageResponse>>, t: Throwable) {
//                Log.d("RETROFIT", "Error")
//            }
//
//            override fun onResponse(
//                call: Call<List<CatImageResponse>>,
//                response: Response<List<CatImageResponse>>
//            ) {
//                Log.d("RETROFIT", response.body()?.size.toString())
//            }
//        })

        // Example 2
        service
            .getImages(parameters = mapOf("limit" to "10", "page" to "0"))
            .subscribe({ cats ->
                for (cat in cats) {
                    Log.d("RETROFIT", "ID = ${cat.id.toString()}, URL = ${cat.url.toString()}")
                }
            }, {
                Log.d("RETROFIT", it.localizedMessage ?: "no message")
            })
    }

}