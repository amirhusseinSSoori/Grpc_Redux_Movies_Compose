package com.amirhusseinsoori.data.repository


import com.amirhusseinsoori.data.dataSource.remote.MovieRemoteSource
import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.exception.GrpcResult
import com.amirhusseinsoori.domain.repository.MovieListRepository
import com.amirhusseinsoori.data.dataSource.remote.MovieRemoteSourceImp
import com.amirhusseinsoori.data.mapper.moviesMapToDomain


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import javax.inject.Inject

class MovieListRepositoryImp @Inject constructor(var local: MovieRemoteSource) :
    MovieListRepository {
    override fun getAllMovies(): Flow<GrpcResult<List<DomainMoviesItem>>> =
        flow<GrpcResult<List<DomainMoviesItem>>> {
            emit(GrpcResult.success(local.getAllMovie().videoListXList.moviesMapToDomain()))
        }.catch { ex ->
            emit(GrpcResult.failure(ex))
        }.flowOn(Dispatchers.IO)
}