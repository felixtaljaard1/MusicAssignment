package com.example.musicmvvm.data.remote.classic

import com.example.musicmvvm.data.entities.classic.ResultClassic
import retrofit2.Response
import retrofit2.http.GET

interface ClassicService {
    @GET("search?term=classick&amp;media=music&amp;entity=song&amp;limit=50")
    suspend fun getAllClassic() : Response<ResultClassic>

//    @GET("search?term=rock&amp;media=music&amp;entity=song&amp;limit=50/{artistId}")
//    suspend fun getRockDetails(@Path("artistId") id: Int): Response<Result>
}