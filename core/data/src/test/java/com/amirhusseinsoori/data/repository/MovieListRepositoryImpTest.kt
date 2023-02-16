package com.amirhusseinsoori.data.repository

import com.amirhusseinsoori.data.dataSource.category.MovieRemoteSource
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
    fun getSearchMovie() {
        runBlocking {
            launch {
                repository.searchMovies("").first()
                Mockito.verify(mockRemoteSource).searchMovies("")
                this.cancel()
            }
            return@runBlocking
        }
    }

}