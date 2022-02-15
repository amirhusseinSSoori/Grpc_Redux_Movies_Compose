package com.amirhusseinsoori.grpckotlin.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope

import com.amirhusseinsoori.grpckotlin.R
import com.amirhusseinsoori.grpckotlin.databinding.FragmentBlockingStubBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_blocking_stub) {

    val viewModel : MovieViewModel by viewModels()
    lateinit var binding: FragmentBlockingStubBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentBlockingStubBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel._state.flowWithLifecycle(lifecycle,Lifecycle.State.STARTED).collect(){
                binding.txtBlockingFragment.text = it
            }
        }

    }



}

