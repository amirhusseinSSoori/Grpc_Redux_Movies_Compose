package com.amirhusseinsoori.data.dataSource.remote

import io.grpc.movienode.VideoListXReply

interface MovieRemoteSource {
    fun getAllMovie(type:String): VideoListXReply
}