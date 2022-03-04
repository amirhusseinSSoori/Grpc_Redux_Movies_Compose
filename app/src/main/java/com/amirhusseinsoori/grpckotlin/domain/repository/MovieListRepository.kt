package com.amirhusseinsoori.grpckotlin.domain.repository

import com.amirhusseinsoori.grpckotlin.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.grpckotlin.domain.exception.GrpcResult
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    fun getAllMovies(): Flow<GrpcResult<List<DomainMoviesItem>>>
}