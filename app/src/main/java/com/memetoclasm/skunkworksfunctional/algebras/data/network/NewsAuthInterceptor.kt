package com.memetoclasm.skunkworksfunctional.algebras.data.network

/**
 * News interceptor that adds the API key for the news source.
 *
 * By Ian Coleman <ian.coleman@tacticaledge.us> on 12/3/19.
 */
import com.memetoclasm.skunkworksfunctional.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class NewsAuthInterceptor(private val apiKey: String = BuildConfig.NEWS_API_KEY) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url()
            .newBuilder()
            .addQueryParameter("apiKey", apiKey)
            .build()

        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}