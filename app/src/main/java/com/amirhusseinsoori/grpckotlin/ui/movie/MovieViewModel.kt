package com.amirhusseinsoori.grpckotlin.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirhusseinsoori.grpckotlin.data.repository.MovieListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(val repository: MovieListRepository) : ViewModel() {


    private val state = MutableStateFlow<String>("")
    val _state = state.asStateFlow()

    init {
        setData()
    }


    private fun setData() {
        viewModelScope.launch {
            repository.getAllMovies().collect() { result ->
                result.fold(onSuccess = {
                    state.value = it.toString()
                }, onFailure = {
                    state.value = it.message!!
                })

            }
        }
    }


}