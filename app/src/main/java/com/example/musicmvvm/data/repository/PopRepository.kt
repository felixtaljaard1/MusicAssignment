package com.example.musicmvvm.data.repository

import com.example.musicmvvm.data.local.PopDAO
import com.example.musicmvvm.data.remote.pop.PopRemoteDataSource
import com.example.musicmvvm.utils.performGetOperation
import javax.inject.Inject

class PopRepository @Inject constructor(
    private val remoteDataSource: PopRemoteDataSource,
    private val localDataSource: PopDAO
){
    fun getPops() = performGetOperation(
        databaseQuery = {localDataSource.getAllPop()},
        networkCall = {remoteDataSource.getPops()},
        saveCallResult = {localDataSource.insertAll(it.results)}
    )
}