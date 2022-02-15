package com.amirhusseinsoori.grpckotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import dagger.hilt.android.AndroidEntryPoint
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.StatusRuntimeException

import io.grpc.stub.StreamObserver
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

import java.util.logging.Level
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }
}