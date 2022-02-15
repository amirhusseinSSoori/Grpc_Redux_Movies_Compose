package com.amirhusseinsoori.grpckotlin.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.amirhusseinsoori.grpckotlin.R
import com.amirhusseinsoori.grpckotlin.databinding.BlockingStubFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import io.grpc.StatusRuntimeException
import io.grpc.mizannodes.*
import kotlinx.coroutines.*
import javax.inject.Inject
@AndroidEntryPoint
class FragmentBlockingStub : Fragment(R.layout.blocking_stub_fragment) {

    lateinit var request: VideoRequest
    lateinit var reply: VideoReply
    @Inject
    lateinit var blockingStub: BodyGrpc.BodyBlockingStub

    lateinit var binding: BlockingStubFragmentBinding
    private val handlerException = CoroutineExceptionHandler { _, throwable ->
        Log.e("Error", "handlerException:${throwable.message}")
    }
    var scope = CoroutineScope(Job() + Dispatchers.Main + handlerException)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = BlockingStubFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        //send Request
        request = VideoRequest.newBuilder().setFilter("").build()

        scope.launch {
            try {
                reply = blockingStub.getVideos(request)
                binding.txtBlockingFragment.text = reply.json

            } catch (ex: StatusRuntimeException) {
                binding.txtBlockingFragment.text = "${ex.message}"
            }
        }
    }



}