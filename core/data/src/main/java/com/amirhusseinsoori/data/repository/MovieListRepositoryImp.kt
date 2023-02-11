package com.amirhusseinsoori.data.repository


import android.util.Log
import arrow.core.Either
import com.amirhusseinsoori.data.dataSource.category.MovieRemoteSource
import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.repository.MovieListRepository
import com.amirhusseinsoori.data.mapper.moviesMapToDomain


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import javax.inject.Inject

class MovieListRepositoryImp @Inject constructor(var local: MovieRemoteSource) :
    MovieListRepository {
    override fun getAllMovies(type: String): Flow<List<DomainMoviesItem>> =
        flow<List<DomainMoviesItem>> {
            emit(local.getAllMovie(type).videoListXList.moviesMapToDomain())
        }.flowOn(Dispatchers.IO)

    override fun searchMovies(type: String): Flow<Either<List<DomainMoviesItem>, Throwable>> =
        flow<Either<List<DomainMoviesItem>, Throwable>> {
            Log.e("ssssss", "searchMovies:${local.searchMovies(type).videoListXList} ", )
            emit(Either.Left(local.searchMovies(type).videoListXList.moviesMapToDomain()))
        }.catch {
                ex ->
            Log.e("ssssss", "searchMovies:${ex.message} ", )
            emit(Either.Right(ex))
        }.flowOn(Dispatchers.IO)


//    =
//    flow<List<DomainMoviesItem>> {
//        emit(local.searchMovies(type).videoListXList.moviesMapToDomain())
//    }.flowOn(Dispatchers.IO)

}
