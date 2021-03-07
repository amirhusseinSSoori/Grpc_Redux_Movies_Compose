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
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

import java.util.logging.Level

class MainActivity : AppCompatActivity() {

    lateinit var turnOnRequest: TurnOnRequest
    lateinit var channel: ManagedChannel
    lateinit var blockingStub: MizanNodesGrpc.MizanNodesBlockingStub
    lateinit var settingReply: SettingReply
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //this port and ip is In Valid
        channel = ManagedChannelBuilder.forAddress("127.0.0.1", 7070).usePlaintext().build()


        blockingStub = MizanNodesGrpc.newBlockingStub(channel)


        //send Request
        turnOnRequest =
            TurnOnRequest.newBuilder().setImei(123645).setPowerOnTime(Date().time).build()

        try {
            //get Response
            settingReply = blockingStub.setTurnOn(turnOnRequest)
            txt.text = "intervalCon :  ${settingReply.intervalCon}" + "    " + "startTime  :  ${settingReply.startTime}"

            Log.e("Success", "${settingReply.intervalCon}" + "    " + "${settingReply.startTime}")
        } catch (ex: StatusRuntimeException) {
            Log.e("Failed", Level.WARNING.toString() + "RPC failed:  " + ex.status.description)


        }


    }
}