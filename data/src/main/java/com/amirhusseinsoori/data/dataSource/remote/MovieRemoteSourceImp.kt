package com.amirhusseinsoori.data.dataSource.remote

import io.grpc.*
import io.grpc.movienode.BodyGrpc
import io.grpc.movienode.VideoListXReply
import io.grpc.movienode.VideoListXRequest

import javax.inject.Inject

class MovieRemoteSourceImp @Inject constructor(var grpcService: BodyGrpc.BodyBlockingStub) :MovieRemoteSource {
    override fun getAllMovie(): VideoListXReply {
        return grpcService.getVideosX(VideoListXRequest.newBuilder().setFilter("ListVideos3").build())
    }
}