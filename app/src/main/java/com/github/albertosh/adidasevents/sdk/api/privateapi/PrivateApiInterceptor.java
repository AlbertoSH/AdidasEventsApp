package com.github.albertosh.adidasevents.sdk.api.privateapi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class PrivateApiInterceptor implements Interceptor {

    private final String token;
    private final String authHeader;

    public PrivateApiInterceptor(String token, String authHeader) {
        this.token = token;
        this.authHeader = authHeader;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        request = request.newBuilder()
                .addHeader(authHeader, token)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build();

        return chain.proceed(request);
    }
}
