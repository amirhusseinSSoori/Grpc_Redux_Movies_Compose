package com.amirhusseinsoori.grpckotlin.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.amirhusseinsoori.grpckotlin.R
import com.amirhusseinsoori.grpckotlin.data.network.TimeoutInterceptor
import com.amirhusseinsoori.grpckotlin.databinding.BlockingStubFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.StatusRuntimeException
import io.grpc.mizannodes.*
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject
@AndroidEntryPoint
class FragmentBlockingStub : Fragment(R.layout.blocking_stub_fragment) {





    lateinit var turnOnRequest: TurnOnRequest
    lateinit var settingReply: SettingReply



    @Inject
    lateinit var blockingStub: MizanNodesGrpc.MizanNodesBlockingStub

    lateinit var binding: BlockingStubFragmentBinding
    private val handlerException = CoroutineExceptionHandler { _, throwable ->
        Log.e("Error", "handlerException:${throwable.message}")
    }

    val job = Job()
    var scope = CoroutineScope(Job() + Dispatchers.Main + handlerException)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = BlockingStubFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)



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