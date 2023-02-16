package com.amirhusseinsoori.data.dataSource


import com.amirhusseinsoori.data.dataSource.category.MovieRemoteSourceImp
import io.grpc.ManagedChannel
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder
import io.grpc.movienode.*
import io.grpc.testing.GrpcCleanupRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class DataSourceTest : BaseServerTest() {


     lateinit var movieRemoteSourceImp: MovieRemoteSourceImp

    @get:Rule
    val grpcCleanup = GrpcCleanupRule()

    lateinit var channel: ManagedChannel


    @Before
    fun setup() {
        setUpService()
        grpcCleanup.register(
            InProcessServerBuilder.forName(serverName)
                .directExecutor().addService(serviceImpl).build().start()
        );
        channel = grpcCleanup.register(
            InProcessChannelBuilder.forName(serverName).directExecutor().build()
        )
        val blockStub = BodyGrpc.newBlockingStub(channel).withInterceptors()
        movieRemoteSourceImp = MovieRemoteSourceImp(blockStub)
    }

    @Test
    fun testGetAllMovies() {
        movieRemoteSourceImp.getAllMovie("")
    }


    @Test
    fun testGetHeader(){
        movieRemoteSourceImp.searchMovies("spider man")
    }


}