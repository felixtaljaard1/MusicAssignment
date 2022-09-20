package com.example.musicmvvm.injection

import android.content.Context
import com.example.musicmvvm.data.local.PopAppDatabase
import com.example.musicmvvm.data.local.PopDAO
import com.example.musicmvvm.data.remote.pop.PopRemoteDataSource
import com.example.musicmvvm.data.remote.pop.PopService
import com.example.musicmvvm.data.repository.PopRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PopAppModule {
    @Provides
    fun providePopService(retrofit: Retrofit) : PopService
    = retrofit.create(PopService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context)
    = PopAppDatabase.getPopDatabase(appContext)

    @Singleton
    @Provides
    fun providePopDAO(appDatabase: PopAppDatabase)
    = appDatabase.popDAO()

    @Singleton
    @Provides
    fun providePopRepository(remoteDataSource: PopRemoteDataSource, localDataSource: PopDAO)
    = PopRepository(remoteDataSource,localDataSource)
}