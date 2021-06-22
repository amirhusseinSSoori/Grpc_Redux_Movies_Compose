package com.amirhusseinsoori.grpckotlin.ui

import io.grpc.*
import java.util.concurrent.TimeUnit

class TimeoutInterceptor : ClientInterceptor {
    override fun <ReqT, RespT> interceptCall(
        method: MethodDescriptor<ReqT, RespT>, callOptions: CallOptions, next: Channel
    ): ClientCall<ReqT, RespT> {
        var callOptions = callOptions
        callOptions = callOptions.withDeadlineAfter(4, TimeUnit.SECONDS)
        return next.newCall(method, callOptions)
    }
}