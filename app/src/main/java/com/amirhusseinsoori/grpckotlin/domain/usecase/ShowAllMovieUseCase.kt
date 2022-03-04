package com.amirhusseinsoori.grpckotlin.domain.usecase

import com.amirhusseinsoori.grpckotlin.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.grpckotlin.domain.exception.GrpcResult
import com.amirhusseinsoori.grpckotlin.domain.repository.MovieListRepository
import com.amirhusseinsoori.grpckotlin.domain.usecase.base.UseCaseImmediate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShowAllMovieUseCase @Inject constructor(private val repository: MovieListRepository) :
    UseCaseImmediate<Flow<GrpcResult<List<DomainMoviesItem>>>>() {
    override suspend fun buildUseCaseImmediate(): Flow<GrpcResult<List<DomainMoviesItem>>> {
        return repository.getAllMovies()
    }
}