package com.amirhusseinsoori.grpckotlin.data.dataSource.remote


import com.google.gson.Gson
import io.grpc.movienode.*

import javax.inject.Inject

class MovieRemoteSource @Inject constructor(var grpcService: BodyGrpc.BodyBlockingStub) {
    fun getAllMovie(): VideoListXReply {
        return grpcService.getVideosX(VideoListXRequest.newBuilder().setFilter("ListVideos3").build())
    }
}