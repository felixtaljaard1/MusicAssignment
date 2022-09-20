package com.example.musicmvvm.data.remote.pop

import com.example.musicmvvm.data.entities.pop.ResultPop
import retrofit2.Response
import retrofit2.http.GET

interface PopService {
    @GET("search?term=pop&amp;media=music&amp;entity=song&amp;limit=50")
    suspend fun getAllPop(): Response<ResultPop>
}