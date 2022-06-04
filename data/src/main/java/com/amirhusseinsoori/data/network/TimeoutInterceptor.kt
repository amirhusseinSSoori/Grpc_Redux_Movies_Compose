package com.amirhusseinsoori.data.network

import io.grpc.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TimeoutInterceptor @Inject constructor() : ClientInterceptor {
    override fun <ReqT, RespT> interceptCall(
        method: MethodDescriptor<ReqT, RespT>, callOptions: CallOptions, next: Channel
    ): ClientCall<ReqT, RespT> {
        var callOptions = callOptions
        callOptions = callOptions.withDeadlineAfter(1, TimeUnit.SECONDS)
        return next.newCall(method, callOptions)
    }
}