package com.amirhusseinsoori.grpckotlin.data.repository

import com.amirhusseinsoori.grpckotlin.data.dataSource.remote.MovieRemoteSource
import com.amirhusseinsoori.grpckotlin.data.network.response.Movies
import io.grpc.mizannodes.BodyGrpc
import io.grpc.mizannodes.VideoReply
import io.grpc.mizannodes.VideoRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import javax.inject.Inject

class MovieListRepository @Inject constructor(var remoteSource: MovieRemoteSource){
    fun getAllMovies(): Flow<Result<Movies>> = flow {
        emit(Result.success(remoteSource.getAllMovie()))
    }.catch{
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)



}