package com.amirhusseinsoori.data.dataSource.category

import io.grpc.movienode.VideoListXReply

interface MovieRemoteSource {
    fun getAllMovie(type:String): VideoListXReply
    fun searchMovies(type:String): VideoListXReply
}