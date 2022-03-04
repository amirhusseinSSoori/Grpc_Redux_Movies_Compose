package com.amirhusseinsoori.grpckotlin.data.repository

import com.amirhusseinsoori.grpckotlin.data.dataSource.remote.MovieRemoteSource
import com.amirhusseinsoori.grpckotlin.data.mapper.moviesMapToDomain
import com.amirhusseinsoori.grpckotlin.domain.DomainMoviesItem
import com.amirhusseinsoori.grpckotlin.domain.exception.GrpcResult

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import javax.inject.Inject

class MovieListRepository @Inject constructor(var remoteSource: MovieRemoteSource) {
    fun getAllMovies(): Flow<GrpcResult<List<DomainMoviesItem>>> = flow {
        emit(GrpcResult.success(remoteSource.getAllMovie().videoListXList.moviesMapToDomain()))
    }.catch { ex ->
        emit(GrpcResult.failure(ex))
    }.flowOn(Dispatchers.IO)


}