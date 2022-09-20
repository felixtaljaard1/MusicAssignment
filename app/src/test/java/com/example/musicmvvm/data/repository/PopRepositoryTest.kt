package com.example.musicmvvm.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.musicmvvm.data.entities.pop.ResultPopItem
import com.example.musicmvvm.data.local.PopDAO
import com.example.musicmvvm.data.remote.pop.PopRemoteDataSource
import com.example.musicmvvm.utils.Resource
import io.mockk.MockKSettings.relaxed
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class PopRepositoryTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val popsObserver: Observer<Resource<List<ResultPopItem>>> = mockk(relaxed = true)
    private val popObserver: Observer<Resource<ResultPopItem>> = mockk(relaxed = true)

    private val remoteDataSource: PopRemoteDataSource = mockk(relaxed = true)
    private val localDataSource: PopDAO = mockk(relaxed = true)
    private lateinit var repository: PopRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp(){
        repository = PopRepository(remoteDataSource, localDataSource)
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `get pops should return livedata of pop API data`(){
        repository.getPops().observeForever(popsObserver)
        verify {popsObserver.onChanged(any())}
    }


}