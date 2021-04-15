package com.tjrwns8024.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(
                "Authorization",
                "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjQ2MTg0NTQyODQsImlkIjoiYWEiLCJyb2xlIjoiYWRtaW4ifQ.DfEJhzhCHPbEUI_7PZxqstBQQa7dp_sOvpMtNrqM5U4"
            )
            .build()

        return chain.proceed(request)
    }
}