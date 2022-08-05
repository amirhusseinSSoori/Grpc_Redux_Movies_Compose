package com.amirhusseinsoori.data.dataSource.remote

import io.grpc.movienode.BodyGrpc
import io.grpc.movienode.VideoListXReply
import io.grpc.movienode.VideoListXRequest

import javax.inject.Inject

class MovieRemoteSourceImp @Inject constructor(var grpcService: BodyGrpc.BodyBlockingStub) :MovieRemoteSource {
    override fun getAllMovie(type:String): VideoListXReply {
        return grpcService.getVideosX(VideoListXRequest.newBuilder().setFilter(type).build())
    }
}