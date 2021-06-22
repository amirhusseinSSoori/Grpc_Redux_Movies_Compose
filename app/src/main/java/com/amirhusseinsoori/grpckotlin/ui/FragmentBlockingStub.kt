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
    lateinit var settingReply: SettingReply
    lateinit var channel: ManagedChannel

    lateinit var blockingStub: MizanNodesGrpc.MizanNodesBlockingStub
    lateinit var binding: BlockingStubFragmentBinding
    private val handlerException = CoroutineExceptionHandler { _, throwable ->
        Log.e("Error", "handlerException:${throwable.message}")
    }

    val job = Job()
    var scope = CoroutineScope(Job() + Dispatchers.Main + handlerException)
    private val scopeMain = CoroutineScope(job + Dispatchers.Main)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = BlockingStubFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)


        channel = ManagedChannelBuilder.forAddress("192.168.0.5", 7070).usePlaintext().build()
        blockingStub = MizanNodesGrpc.newBlockingStub(channel).withInterceptors(TimeoutInterceptor())


        //send Request
        turnOnRequest = TurnOnRequest.newBuilder().setAndroidId("111").setPowerOnTime(Date().time).build()

        scope.launch {
            try {
                settingReply = blockingStub.setTurnOn(turnOnRequest)
                binding.txtBlockingStubFFragment.text = "${settingReply.startTime}  ${settingReply.intervalCon}"

            } catch (ex: StatusRuntimeException) {
                binding.txtBlockingStubFFragment.text = "${ex.message}"
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}