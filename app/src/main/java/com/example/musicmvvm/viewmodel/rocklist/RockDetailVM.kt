package com.example.musicmvvm.viewmodel.rocklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.musicmvvm.data.entities.rock.Result
import com.example.musicmvvm.data.repository.RockRepository
import com.example.musicmvvm.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RockDetailVM @Inject constructor(
    private val repository: RockRepository) : ViewModel(){

        private val _id = MutableLiveData<Int>()

    private val _rock = _id.switchMap { id ->
        repository.getRockDetailsData(id)
    }

    val rock : LiveData<Resource<Result>> = _rock

    fun startDetailsCall(id: Int){
        _id.value = id
    }
    }