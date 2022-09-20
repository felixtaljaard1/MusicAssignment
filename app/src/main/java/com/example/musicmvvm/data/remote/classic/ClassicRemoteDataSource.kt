package com.example.musicmvvm.data.remote.classic

import com.example.musicmvvm.data.remote.rock.BaseDataSource
import com.example.musicmvvm.data.remote.classic.ClassicService
import javax.inject.Inject

class ClassicRemoteDataSource @Inject constructor(
    private val classicService: ClassicService
) : BaseDataSource() {
    suspend fun getClassics() = getResult { classicService.getAllClassic() }
    //suspend fun getClassicById(id: Int) = getResult { classicService.getRockDetails(id) }
}