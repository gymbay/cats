package ru.gymbay.cats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import api.services.ImagesService
import di.CatsApplication
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var service: ImagesService

    override fun onCreate(savedInstanceState: Bundle?) {
        // Make Dagger instantiate @Inject fields
        (application as CatsApplication).applicationComponent.inject(this)
        // Now service is available

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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