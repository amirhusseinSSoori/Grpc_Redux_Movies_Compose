package com.amirhusseinsoori.data.repository


import com.amirhusseinsoori.data.dataSource.remote.MovieRemoteSource
import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.repository.MovieListRepository
import com.amirhusseinsoori.data.mapper.moviesMapToDomain


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import javax.inject.Inject

class MovieListRepositoryImp @Inject constructor(var local: MovieRemoteSource) :
    MovieListRepository {
    override fun getAllMovies(type:String): Flow<List<DomainMoviesItem>> =
        flow<List<DomainMoviesItem>> {
            emit(local.getAllMovie(type).videoListXList.moviesMapToDomain())
        }.flowOn(Dispatchers.IO)
}