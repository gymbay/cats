package api.services

import api.Provider
import api.models.images.CatImage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

class ImagesService {

    interface Images {
        @GET("images/search")
        fun getImages(@QueryMap parameters: Map<String, String> = emptyMap()): Call<List<CatImage>>
    }

    fun createService() : Images {
        val provider = Provider().createProvider()
        return provider.create(Images::class.java)
    }

}

