package com.amirhusseinsoori.core.dataSource.remote

import io.grpc.*
import io.grpc.movienode.BodyGrpc
import io.grpc.movienode.VideoListXReply
import io.grpc.movienode.VideoListXRequest

import javax.inject.Inject

class MovieRemoteSource @Inject constructor(var grpcService: BodyGrpc.BodyBlockingStub) {
    fun getAllMovie(): VideoListXReply {
        return grpcService.getVideosX(VideoListXRequest.newBuilder().setFilter("ListVideos3").build())
    }
}