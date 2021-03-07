package com.amirhusseinsoori.grpckotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.StatusRuntimeException
import io.grpc.mizannodes.MizanNodesGrpc
import io.grpc.mizannodes.SettingReply
import io.grpc.mizannodes.TurnOnRequest
import io.grpc.stub.StreamObserver
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

import java.util.logging.Level

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}