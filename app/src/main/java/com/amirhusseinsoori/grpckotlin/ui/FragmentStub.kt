package com.amirhusseinsoori.grpckotlin.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.amirhusseinsoori.grpckotlin.R
import com.amirhusseinsoori.grpckotlin.databinding.StubFragmentBinding
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.mizannodes.*
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.*
import java.util.*
import java.util.logging.Level

class FragmentStub : Fragment(R.layout.stub_fragment) {


    lateinit var id: ID
    lateinit var mac:MAC


    lateinit var channel: ManagedChannel
    lateinit var newStub: MizanNodesGrpc.MizanNodesStub
    lateinit var binding: StubFragmentBinding

    val job = Job()
    private val scope= CoroutineScope(job + Dispatchers.Main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = StubFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        //create Channel
        channel = ManagedChannelBuilder.forAddress("192.168.0.5", 7070).usePlaintext().build()
        newStub = MizanNodesGrpc.newStub(channel).withInterceptors(TimeoutInterceptor())
        //sendRequest
        id = ID.newBuilder().setAndroidId("111").build()

        newStub.getNewWifiMAC(id, object : StreamObserver<MAC> {
            override fun onNext(value: MAC?) {
                Log.e("Status", "onNext:   $value")
                scope.launch {
                    binding.txtStubFFragment.text = "${value!!.macAdr}   "
                }

            }

            override fun onError(t: Throwable?) {
                Log.e("Status", "onError:   ${t!!.message}")
                scope.launch {
                    binding.txtStubFFragment.text = t.message
                }

            }
            override fun onCompleted() {
                Log.e("Status", "onCompleted:  ")
            }

        })


    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }


}