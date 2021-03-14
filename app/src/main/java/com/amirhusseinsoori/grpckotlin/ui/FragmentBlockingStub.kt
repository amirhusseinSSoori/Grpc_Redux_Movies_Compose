package com.amirhusseinsoori.grpckotlin.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.amirhusseinsoori.grpckotlin.R
import com.amirhusseinsoori.grpckotlin.databinding.BlockingStubFragmentBinding
import io.grpc.Context.current
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.mizannodes.*
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.logging.Level
import kotlin.math.log

class FragmentBlockingStub : Fragment(R.layout.blocking_stub_fragment) {
    lateinit var turnOnRequest: TurnOnRequest
    lateinit var channel: ManagedChannel

    lateinit var blockingStub: MizanNodesGrpc.MizanNodesBlockingStub
    lateinit var settingReply: SettingReply
    lateinit var commandReply: CommandReply
    lateinit var binding: BlockingStubFragmentBinding
    private val handlerException = CoroutineExceptionHandler { coroutineContext, throwable ->

        Log.e("Error", "onViewCreated:${throwable.message}")

    }
    lateinit var setInfoRequest: PhoneInfoRequest
    val job = Job()
    var scopeIo = CoroutineScope(Job() + Dispatchers.IO + handlerException)
    private val scopeMain = CoroutineScope(job + Dispatchers.Main)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = BlockingStubFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)


        channel = ManagedChannelBuilder.forAddress("192.168.0.5", 7070).usePlaintext().build()
        blockingStub = MizanNodesGrpc.newBlockingStub(channel)


        //send Request

        setInfoRequest = PhoneInfoRequest.newBuilder().setImeiNo(123).setBatteryLevel(50)
            .setOperatorName("iranceel").setInternetPack(10).build()

        try {
            scopeIo.launch {

                commandReply = blockingStub.setPhoneInfo(setInfoRequest)
                Log.e("Success", "${commandReply.isNotify}")
            }


        } catch (ex: StatusRuntimeException) {
            Log.e("Success", "${ex.message}")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}