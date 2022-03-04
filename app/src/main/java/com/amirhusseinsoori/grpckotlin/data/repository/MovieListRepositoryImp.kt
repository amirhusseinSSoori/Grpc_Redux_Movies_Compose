package com.amirhusseinsoori.grpckotlin.data.repository

import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.exception.GrpcResult
import com.amirhusseinsoori.domain.repository.MovieListRepository
import com.amirhusseinsoori.grpckotlin.data.dataSource.remote.MovieRemoteSource
import com.amirhusseinsoori.grpckotlin.data.mapper.moviesMapToDomain


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import javax.inject.Inject

class MovieListRepositoryImp @Inject constructor(var remoteSource: MovieRemoteSource):
    MovieListRepository {
    override fun getAllMovies(): Flow<GrpcResult<List<DomainMoviesItem>>> = flow {
        emit(GrpcResult.success(remoteSource.getAllMovie().videoListXList.moviesMapToDomain()))
    }.catch { ex ->
        emit(GrpcResult.failure(ex))
    }.flowOn(Dispatchers.IO)
}