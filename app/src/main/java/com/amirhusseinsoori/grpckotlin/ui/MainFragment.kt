package com.amirhusseinsoori.grpckotlin.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.amirhusseinsoori.grpckotlin.R
import com.amirhusseinsoori.grpckotlin.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {
    lateinit var binding: MainFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = MainFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.btnMainFBlockingStub.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_fragmentBlockingStub)
        }

    }
}