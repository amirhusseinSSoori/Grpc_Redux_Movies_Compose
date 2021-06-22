package com.amirhusseinsoori.grpckotlin.data.di

import com.amirhusseinsoori.grpckotlin.data.network.TimeoutInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.mizannodes.MizanNodesGrpc
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GrpcModule {


    @Provides
    fun setBlockingStub(timeout: TimeoutInterceptor, channel: ManagedChannel): MizanNodesGrpc.MizanNodesBlockingStub {
        return MizanNodesGrpc.newBlockingStub(
            channel
        ).withInterceptors(timeout)
    }


    @Provides
    fun setStub(timeout: TimeoutInterceptor, channel: ManagedChannel): MizanNodesGrpc.MizanNodesStub {
        return MizanNodesGrpc.newStub(
            channel
        ).withInterceptors(timeout)
    }


    @Singleton
    @Provides
    fun provideChannel(): ManagedChannel {
        return ManagedChannelBuilder.forAddress(
            "192.168.0.5",
            7070)
            .usePlaintext()
            .build()
    }


}