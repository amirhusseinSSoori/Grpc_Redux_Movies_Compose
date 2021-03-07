package com.amirhusseinsoori.grpckotlin.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.amirhusseinsoori.grpckotlin.R
import com.amirhusseinsoori.grpckotlin.databinding.BlockingStubFragmentBinding
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.StatusRuntimeException
import io.grpc.mizannodes.MizanNodesGrpc
import io.grpc.mizannodes.SettingReply
import io.grpc.mizannodes.TurnOnRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import java.util.logging.Level

class FragmentBlockingStub : Fragment(R.layout.blocking_stub_fragment) {
    lateinit var turnOnRequest: TurnOnRequest
    lateinit var channel: ManagedChannel
    lateinit var blockingStub: MizanNodesGrpc.MizanNodesBlockingStub
    lateinit var settingReply: SettingReply
    lateinit var binding: BlockingStubFragmentBinding
    val job = Job()
    private val scopeMain = CoroutineScope(job + Dispatchers.Main)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = BlockingStubFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        channel = ManagedChannelBuilder.forAddress("YourIP", 7070).usePlaintext().build()


        blockingStub = MizanNodesGrpc.newBlockingStub(channel)


        //send Request
        turnOnRequest =
            TurnOnRequest.newBuilder().setImei(123).setPowerOnTime(Date().time).build()

        try {

            //get Response
            settingReply = blockingStub.setTurnOn(turnOnRequest)
            binding.txtBlockingStubFFragment.text =
                "intervalCon :  ${settingReply.intervalCon}" + "    " + "startTime  :  ${settingReply.startTime}"

            Log.e("Success", "${settingReply.intervalCon}" + "    " + "${settingReply.startTime}")
        } catch (ex: StatusRuntimeException) {

            Log.e("Failed", Level.WARNING.toString() + "RPC failed:  " + ex.status.description)
            scopeMain.launch {
                binding.txtBlockingStubFFragment.text = ex.status.description
            }
        } catch (ex: Throwable) {
            scopeMain.launch {
                binding.txtBlockingStubFFragment.text = ex.message
            }

            Log.e("Failed", Level.WARNING.toString() + "RPC failed:  " + ex.message)
        }

    }
}