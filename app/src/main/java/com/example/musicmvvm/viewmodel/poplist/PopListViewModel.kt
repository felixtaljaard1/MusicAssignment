package com.example.musicmvvm.viewmodel.poplist

import androidx.lifecycle.ViewModel
import com.example.musicmvvm.data.repository.PopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PopListViewModel @Inject constructor(
    repository: PopRepository
) : ViewModel(){
    val repository = repository.getPops()
}