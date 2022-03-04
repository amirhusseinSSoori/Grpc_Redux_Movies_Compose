package com.amirhusseinsoori.grpckotlin.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amirhusseinsoori.grpckotlin.data.repository.MovieListRepositoryImp
import com.amirhusseinsoori.grpckotlin.domain.exception.fold
import com.amirhusseinsoori.grpckotlin.domain.usecase.ShowAllMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(private val showAllMovieUseCase: ShowAllMovieUseCase) : ViewModel() {


    private val state = MutableStateFlow<String>("")
    val _state = state.asStateFlow()

    init {
        setData()
    }

    private fun setData() {
        viewModelScope.launch {
            showAllMovieUseCase.execute().collect() { result ->
                result.fold(onSuccess = {
                    state.value = it.toString()
                }, onLoading = {
                    state.value = it.toString()
                }, onFailure = {
                    state.value = it.message!!
                })

            }
        }
    }


}