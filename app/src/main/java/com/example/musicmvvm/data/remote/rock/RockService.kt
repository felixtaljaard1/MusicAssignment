package com.example.musicmvvm.data.remote.rock

import com.example.musicmvvm.data.entities.rock.Result
import com.example.musicmvvm.data.entities.rock.ResultRock
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RockService {
    @GET("search?term=rock&amp;media=music&amp;entity=song&amp;limit=50")
    suspend fun getAllRock() : Response<ResultRock>

    @GET("search?term=rock&amp;media=music&amp;entity=song&amp;limit=50/{artistId}")
    suspend fun getRockDetails(@Path("artistId") id: Int): Response<Result>
}