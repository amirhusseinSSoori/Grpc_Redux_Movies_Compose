package com.amirhusseinsoori.data.repository

import com.amirhusseinsoori.data.dataSource.remote.MovieRemoteSource
import com.amirhusseinsoori.domain.repository.MovieListRepository
import com.amirhusseinsoori.domain.usecase.ShowAllMovieUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class MovieListRepositoryImpTest{
    private lateinit var repository: MovieListRepositoryImp
    private val mockRemoteSource = Mockito.mock(MovieRemoteSource::class.java)

    @Before
    fun setup() {
        repository = MovieListRepositoryImp(mockRemoteSource)
    }

    @Test
    fun getShowAllMovie() {
        runBlocking {
            launch {
                repository.getAllMovies()
                this.cancel()
            }
            return@runBlocking
        }
    }

}