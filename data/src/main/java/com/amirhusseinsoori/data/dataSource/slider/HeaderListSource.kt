package com.amirhusseinsoori.data.dataSource.slider

import io.grpc.movienode.VideoHeaderXReply

interface HeaderListSource {
    fun getSliderMovie(type: String): VideoHeaderXReply
}