package com.memetoclasm.skunkworksfunctional.runtime

import android.app.Application
import android.content.Context
import com.memetoclasm.skunkworksfunctional.algebras.data.network.NewsApiService
import com.memetoclasm.skunkworksfunctional.algebras.data.network.NewsAuthInterceptor
import com.memetoclasm.skunkworksfunctional.runtime.context.RuntimeContext
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * This is a placeholder description of this class.
 *
 * By Ian Coleman <ian.coleman@tacticaledge.us> on 12/3/19.
 */
class SkunkworksFunctionalApplication : Application() {

    val runtimeContext by lazy {
        RuntimeContext(
            bgDispatcher = Dispatchers.IO,
            mainDispatcher = Dispatchers.Main,
            newsService = newsService
            // TODO: Add any services necessary (e.g. newsService = newsService)
        )
    }

    private val newsService: NewsApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient)
            .build()

        retrofit.create<NewsApiService>(NewsApiService::class.java)
    }


    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addNetworkInterceptor(NewsAuthInterceptor())
            .build()
    }
}

fun Context.application(): SkunkworksFunctionalApplication = applicationContext as SkunkworksFunctionalApplication