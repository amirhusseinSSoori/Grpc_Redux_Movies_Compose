package com.amirhusseinsoori.domain.usecase
import com.amirhusseinsoori.domain.repository.MovieListRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito




class ShowAllMovieUseCaseTest {
    private lateinit var useCase: ShowAllMovieUseCase
    private val mockRepository = Mockito.mock(MovieListRepository::class.java)
    @Before
    fun setup() {
        useCase = ShowAllMovieUseCase(mockRepository)
    }

    @Test
    fun getShowAllMovie() {
        runBlocking {
            launch {
                useCase.execute()
                Mockito.verify(mockRepository).getAllMovies()
                this.cancel()
            }
            return@runBlocking
        }
    }
}