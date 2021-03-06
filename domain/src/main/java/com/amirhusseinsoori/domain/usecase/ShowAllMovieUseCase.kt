package com.amirhusseinsoori.domain.usecase

import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.exception.GrpcResult
import com.amirhusseinsoori.domain.repository.MovieListRepository
import com.amirhusseinsoori.domain.usecase.base.UseCaseWithParamsImmediate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShowAllMovieUseCase @Inject constructor(private val repository: MovieListRepository) :
    UseCaseWithParamsImmediate<String, Flow<List<DomainMoviesItem>>>() {
    override suspend fun buildUseCaseImmediate(params: String): Flow<List<DomainMoviesItem>> {
        return repository.getAllMovies(params)
    }
}