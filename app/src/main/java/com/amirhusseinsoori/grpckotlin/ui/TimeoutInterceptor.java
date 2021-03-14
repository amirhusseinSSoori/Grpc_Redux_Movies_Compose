package com.amirhusseinsoori.grpckotlin.ui;

import java.util.concurrent.TimeUnit;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.MethodDescriptor;

public class TimeoutInterceptor implements ClientInterceptor {
    @Override public <ReqT,RespT> ClientCall<ReqT,RespT>  interceptCall(
            MethodDescriptor<ReqT,RespT> method, CallOptions callOptions, Channel next) {
        callOptions = callOptions.withDeadlineAfter(10, TimeUnit.SECONDS);
        return next.newCall(method, callOptions);
    }
}
