package com.example.musicmvvm.data.repository

import com.example.musicmvvm.data.local.ClassicDAO
import com.example.musicmvvm.data.remote.classic.ClassicRemoteDataSource
import com.example.musicmvvm.utils.performGetOperation
import javax.inject.Inject

class ClassicRepository @Inject constructor(
    private val remoteDataSource: ClassicRemoteDataSource,
    private val localDataSource: ClassicDAO
){
    fun getClassics() = performGetOperation(
        databaseQuery = {localDataSource.getAllClassic()},
        networkCall = {remoteDataSource.getClassics()},
        saveCallResult = {localDataSource.insertAll(it.results)}
    )

//    fun getClassicDetailsData(id: Int) = performGetOperation(
//        databaseQuery = {localDataSource.getClassic(id)},
//        networkCall = {remoteDataSource.getClassicById(id)},
//        saveCallResult = {localDataSource.insertDetails(it)}
//    )

}