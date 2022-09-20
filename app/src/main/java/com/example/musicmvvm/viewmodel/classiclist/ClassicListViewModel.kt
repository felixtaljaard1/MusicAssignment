package com.example.musicmvvm.viewmodel.classiclist

import androidx.lifecycle.ViewModel
import com.example.musicmvvm.data.repository.ClassicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ClassicListViewModel @Inject constructor(
    repository: ClassicRepository
) : ViewModel() {
    val repository = repository.getClassics()
}