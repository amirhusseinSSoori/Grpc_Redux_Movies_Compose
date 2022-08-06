package com.amirhusseinsoori.domain.usecase

import arrow.core.Either
import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.repository.MovieListRepository
import com.amirhusseinsoori.domain.usecase.base.UseCaseWithParamsImmediate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(private val repository: MovieListRepository) :
    UseCaseWithParamsImmediate<String, Flow<Either<List<DomainMoviesItem>,Throwable>>>() {
    override suspend fun buildUseCaseImmediate(params: String): Flow<Either<List<DomainMoviesItem>, Throwable>> {
        return repository.searchMovies(params)
    }
}