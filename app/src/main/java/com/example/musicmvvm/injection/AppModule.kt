package com.example.musicmvvm.injection

import android.content.Context
import com.example.musicmvvm.data.local.AppDatabase
import com.example.musicmvvm.data.local.RockDAO
import com.example.musicmvvm.data.remote.rock.RockRemoteDataSource
import com.example.musicmvvm.data.remote.rock.RockService
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
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGSON(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://itunes.apple.com/")
        .addConverterFactory((GsonConverterFactory.create(gson)))
        .build()

    @Provides
    fun provideRockService(retrofit: Retrofit) : RockService
    = retrofit.create(RockService::class.java)

    @Singleton
    @Provides
    fun provideRockRemoteDataSource(rockService: RockService)
    =RockRemoteDataSource(rockService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context)
    = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideRockDAO(appDatabase: AppDatabase)
    = appDatabase.rockDAO()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RockRemoteDataSource, localDataSource: RockDAO)
    =RockRepository(remoteDataSource, localDataSource)
}