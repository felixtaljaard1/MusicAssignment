package com.example.musicmvvm.data.remote.rock

import com.example.musicmvvm.data.remote.classic.ClassicService
import javax.inject.Inject

class RockRemoteDataSource @Inject constructor(
    private val rockService: RockService
) : BaseDataSource() {
        suspend fun getRocks() = getResult { rockService.getAllRock() }
     suspend fun getRockById(id: Int) = getResult { rockService.getRockDetails(id) }
    }