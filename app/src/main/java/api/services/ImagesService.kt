package api.services

import api.models.images.CatImageResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ImagesService {
    @GET("images/search")
    fun getImages(@QueryMap parameters: Map<String, String> = emptyMap()): Observable<List<CatImageResponse>>
}

