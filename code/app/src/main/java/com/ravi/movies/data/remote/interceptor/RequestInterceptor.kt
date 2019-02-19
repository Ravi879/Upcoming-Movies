package com.ravi.movies.data.remote.interceptor


import com.ravi.movies.AppEntry
import com.ravi.movies.R
import com.ravi.movies.util.Constants
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url()
        val api_key = AppEntry.getAppContext.getString(R.string.api_key)
        val url = originalUrl.newBuilder()
            .addQueryParameter("api_key", api_key)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
