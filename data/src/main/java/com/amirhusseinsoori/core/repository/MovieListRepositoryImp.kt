package com.amirhusseinsoori.core.repository


import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.exception.GrpcResult
import com.amirhusseinsoori.domain.repository.MovieListRepository
import com.amirhusseinsoori.core.dataSource.remote.MovieRemoteSource
import com.amirhusseinsoori.data.mapper.moviesMapToDomain


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import javax.inject.Inject

class MovieListRepositoryImp @Inject constructor(var remoteSource: MovieRemoteSource) :
    MovieListRepository {
    override fun getAllMovies(): Flow<GrpcResult<List<DomainMoviesItem>>> =
        flow<GrpcResult<List<DomainMoviesItem>>> {
            emit(GrpcResult.success(remoteSource.getAllMovie().videoListXList.moviesMapToDomain()))
        }.catch { ex ->
            emit(GrpcResult.failure(ex))
        }.flowOn(Dispatchers.IO)
}