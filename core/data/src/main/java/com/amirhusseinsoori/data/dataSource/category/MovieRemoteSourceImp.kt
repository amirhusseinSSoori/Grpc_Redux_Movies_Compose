package com.amirhusseinsoori.data.dataSource.category

import io.grpc.movienode.BodyGrpc
import io.grpc.movienode.VideoListXReply
import io.grpc.movienode.VideoListXRequest

import javax.inject.Inject

class MovieRemoteSourceImp @Inject constructor(var grpcService: BodyGrpc.BodyBlockingStub) :MovieRemoteSource {
    override fun getAllMovie(type:String): VideoListXReply {
        return grpcService.getVideosX(VideoListXRequest.newBuilder().setFilter(type).build())
    }

    override fun searchMovies(type: String): VideoListXReply {
        return grpcService.searchVideosX(VideoListXRequest.newBuilder().setFilter(type).build())
    }
}