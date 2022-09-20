package com.example.musicmvvm.injection

import android.content.Context
import com.example.musicmvvm.data.local.AppDatabase
import com.example.musicmvvm.data.local.ClassicAppDatabase
import com.example.musicmvvm.data.local.ClassicDAO
import com.example.musicmvvm.data.remote.classic.ClassicRemoteDataSource
import com.example.musicmvvm.data.remote.classic.ClassicService
import com.example.musicmvvm.data.remote.rock.RockRemoteDataSource
import com.example.musicmvvm.data.remote.rock.RockService
import com.example.musicmvvm.data.repository.ClassicRepository
import com.example.musicmvvm.data.repository.RockRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClassicAppModule {

//    @Singleton
//    @Provides
//    fun provideGSON(): Gson = GsonBuilder().create()
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
//        .baseUrl("https://itunes.apple.com/")
//        .addConverterFactory((GsonConverterFactory.create(gson)))
//        .build()

    @Provides
    fun provideClassicService(retrofit: Retrofit) : ClassicService
            = retrofit.create(ClassicService::class.java)

    @Singleton
    @Provides
    fun provideClassicRemoteDataSource(classicService: ClassicService)
            = ClassicRemoteDataSource(classicService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context)
            = ClassicAppDatabase.getClassicDatabase(appContext)

    @Singleton
    @Provides
    fun provideClassicDAO(appDatabase: ClassicAppDatabase)
            = appDatabase.classicDAO()

    @Singleton
    @Provides
    fun provideClassicRepository(remoteDataSource: ClassicRemoteDataSource, localDataSource: ClassicDAO)
            = ClassicRepository(remoteDataSource, localDataSource)
}