package com.example.musicmvvm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicmvvm.data.repository.RockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
//class RockListViewModel @Inject constructor(
//    repository: RockRepository) : ViewModel() {
//    val repository = repository.getAllRock()
//}