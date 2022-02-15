package com.amirhusseinsoori.grpckotlin.data.repository

import io.grpc.mizannodes.BodyGrpc
import io.grpc.mizannodes.VideoReply
import io.grpc.mizannodes.VideoRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import javax.inject.Inject

class MovieListRepository @Inject constructor(var grpcService: BodyGrpc.BodyBlockingStub) {
    fun getAllMovies(): Flow<Result<VideoReply>> = flow {
        emit(Result.success(getResponse()))
    }.catch{
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)


    private fun getResponse():VideoReply{
        return grpcService.getVideos(VideoRequest.newBuilder().setFilter("").build())
    }
}