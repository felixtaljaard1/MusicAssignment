package com.example.musicmvvm.data.repository

import com.example.musicmvvm.data.local.RockDAO
import com.example.musicmvvm.data.remote.rock.RockRemoteDataSource
import com.example.musicmvvm.utils.performGetOperation
import javax.inject.Inject

class RockRepository @Inject constructor(
    private val remoteDataSource: RockRemoteDataSource,
    private val localDataSource: RockDAO
){
    fun getRocks() = performGetOperation(
        databaseQuery = {localDataSource.getAllRock()},
        networkCall = {remoteDataSource.getRocks()},
        saveCallResult = {localDataSource.insertAll(it.results)}
    )

    fun getRockDetailsData(id: Int) = performGetOperation(
        databaseQuery = {localDataSource.getRock(id)},
        networkCall = {remoteDataSource.getRockById(id)},
        saveCallResult = {localDataSource.insertDetails(it)}
    )

}