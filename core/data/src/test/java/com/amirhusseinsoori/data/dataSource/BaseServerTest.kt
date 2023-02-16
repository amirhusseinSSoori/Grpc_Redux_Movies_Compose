package com.amirhusseinsoori.data.dataSource

import io.grpc.inprocess.InProcessServerBuilder
import io.grpc.movienode.*
import io.grpc.stub.StreamObserver

open class BaseServerTest {
    protected val serverName = InProcessServerBuilder.generateName()
    protected lateinit var serviceImpl: BodyGrpc.BodyImplBase



    fun setUpService(){
        serviceImpl = object : BodyGrpc.BodyImplBase() {
            override fun getVideosX(
                request: VideoListXRequest?,
                responseObserver: StreamObserver<VideoListXReply>?
            ) {
                responseObserver?.onNext(VideoListXReply.newBuilder().build())
                responseObserver?.onCompleted()
            }

            override fun searchVideosX(
                request: VideoListXRequest?,
                responseObserver: StreamObserver<VideoListXReply>?
            ) {
                responseObserver?.onNext(VideoListXReply.newBuilder().build())
                responseObserver?.onCompleted()
            }
        }


    }

}