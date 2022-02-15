package com.amirhusseinsoori.grpckotlin.data.dataSource.remote

import com.amirhusseinsoori.grpckotlin.data.network.response.Movies
import com.google.gson.Gson
import io.grpc.mizannodes.BodyGrpc
import io.grpc.mizannodes.VideoReply
import io.grpc.mizannodes.VideoRequest
import javax.inject.Inject

class MovieRemoteSource @Inject constructor(var grpcService: BodyGrpc.BodyBlockingStub) {


    fun getAllMovie(): Movies {
        return Gson().fromJson(getResponse().json, Movies::class.java)
    }

    private fun getResponse(): VideoReply {
        return grpcService.getVideos(VideoRequest.newBuilder().setFilter("").build())
    }
}