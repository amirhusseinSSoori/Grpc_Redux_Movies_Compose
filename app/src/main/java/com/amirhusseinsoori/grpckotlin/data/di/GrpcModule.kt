package com.amirhusseinsoori.grpckotlin.data.di

import com.amirhusseinsoori.grpckotlin.data.network.TimeoutInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.mizannodes.BodyGrpc

import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GrpcModule {


    @Provides
    fun setBlockingStub(
        timeout: TimeoutInterceptor,
        channel: ManagedChannel
    ): BodyGrpc.BodyBlockingStub {
        return BodyGrpc.newBlockingStub(
            channel
        ).withInterceptors(timeout)
    }


    @Singleton
    @Provides
    fun provideChannel(): ManagedChannel {
        return ManagedChannelBuilder.forAddress(
            "192.168.14.147",
            50051
        )
            .executor(Executors.newSingleThreadExecutor())
            .usePlaintext()
            .build()
    }


}