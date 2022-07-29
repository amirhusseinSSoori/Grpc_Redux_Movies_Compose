package com.amirhusseinsoori.data.dataSource.slider

import io.grpc.movienode.BodyGrpc
import io.grpc.movienode.VideoHeaderXReply
import io.grpc.movienode.VideoHeaderXRequest
import javax.inject.Inject

class HeaderListSourceImp @Inject constructor(var grpcService: BodyGrpc.BodyBlockingStub):HeaderListSource {
    override fun getSliderMovie(type: String): VideoHeaderXReply {
        return  grpcService.getHeadersX(VideoHeaderXRequest.newBuilder().setFilter(type).build())
    }
}