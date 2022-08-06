package com.amirhusseinsoori.domain.repository

import arrow.core.Either
import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.exception.GrpcResult
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    fun getAllMovies(type: String): Flow<List<DomainMoviesItem>>
    fun searchMovies(type: String): Flow<Either<List<DomainMoviesItem>,Throwable>>
}