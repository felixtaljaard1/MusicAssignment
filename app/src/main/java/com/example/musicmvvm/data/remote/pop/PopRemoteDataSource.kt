package com.example.musicmvvm.data.remote.pop

import com.example.musicmvvm.data.remote.rock.BaseDataSource
import javax.inject.Inject

class PopRemoteDataSource @Inject constructor(
    private val popService: PopService
) : BaseDataSource(){
    suspend fun getPops() = getResult { popService.getAllPop() }
}