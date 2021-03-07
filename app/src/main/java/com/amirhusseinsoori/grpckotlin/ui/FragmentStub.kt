package com.amirhusseinsoori.grpckotlin.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.amirhusseinsoori.grpckotlin.R
import com.amirhusseinsoori.grpckotlin.databinding.StubFragmentBinding
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.mizannodes.MizanNodesGrpc
import io.grpc.mizannodes.SettingReply
import io.grpc.mizannodes.TurnOnRequest
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.*
import java.util.*
import java.util.logging.Level

class FragmentStub : Fragment(R.layout.stub_fragment) {


    lateinit var turnOnRequest: TurnOnRequest
    lateinit var channel: ManagedChannel
    lateinit var newStub: MizanNodesGrpc.MizanNodesStub
    lateinit var binding: StubFragmentBinding
    val job = Job()
    private val scopeMain = CoroutineScope(job + Dispatchers.Main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = StubFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        //create Channel
        channel = ManagedChannelBuilder.forAddress("YourIP", 7070).usePlaintext().build()
        newStub = MizanNodesGrpc.newStub(channel)
        //sendRequest
        turnOnRequest =
            TurnOnRequest.newBuilder().setImei(123).setPowerOnTime(Date().time).build()

        newStub.setTurnOn(turnOnRequest, object : StreamObserver<SettingReply> {
            override fun onNext(value: SettingReply?) {
                Log.e("TAG", "onNext:   $value")
                binding.txtStubFFragment.text = "${value!!.startTime}   ${value!!.intervalCon} "
            }

            override fun onError(t: Throwable?) {
                Log.e("TAG", "onError:  ${Level.WARNING}  ${t!!.message}")
                scopeMain.launch {
                    binding.txtStubFFragment.text = t.message
                }

            }

            override fun onCompleted() {
                Log.e("TAG", "onCompleted:  ")
            }

        })


    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }


}