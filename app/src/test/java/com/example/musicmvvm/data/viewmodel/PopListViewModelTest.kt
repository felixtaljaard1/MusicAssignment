package com.example.musicmvvm.data.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.musicmvvm.data.entities.pop.ResultPopItem
import com.example.musicmvvm.data.repository.PopRepository
import com.example.musicmvvm.utils.Resource
import com.example.musicmvvm.viewmodel.poplist.PopListViewModel
import io.mockk.MockKSettings.relaxed
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PopListViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PopListViewModel
    private val popList = listOf(
        ResultPopItem(1, "Prince", "","","",2.99),
        ResultPopItem(2, "Queen", "","","",1.99)
    )

    private val repository: PopRepository = mockk(relaxed = true){
        every { getPops() } returns MutableLiveData(Resource.success(popList))
    }

    private val popObserver: Observer<Resource<List<ResultPopItem>>> =
        mockk(relaxed = true)

    @Before
    fun setUp() {
        viewModel = PopListViewModel(repository)
        viewModel.repository.observeForever(popObserver)
    }

    @Test
    fun `get pops should return emit list of pop songs`(){
        verify{popObserver.onChanged(Resource.success(popList))}
        assert(viewModel.repository.value == Resource.success(popList))
    }
}