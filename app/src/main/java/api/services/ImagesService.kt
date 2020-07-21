package api.services

import api.Provider
import api.models.images.CatImageResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*
import javax.inject.Inject

class ImagesService @Inject constructor() {

    interface Images {
        @GET("images/search")
        fun getImages(@QueryMap parameters: Map<String, String> = emptyMap()): Observable<List<CatImageResponse>>
    }

    fun createService() : Images {
        val provider = Provider().createProvider()
        return provider.create(Images::class.java)
    }

}

