package com.memetoclasm.skunkworksfunctional.algebras.data.network

import com.memetoclasm.skunkworksfunctional.algebras.data.network.dto.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This is a placeholder description of this class.
 *
 * By Ian Coleman <ian.coleman@tacticaledge.us> on 12/3/19.
 */
interface NewsApiService {

    @GET("everything")
    fun fetchNews(@Query("q") query: String): Call<NewsResponse>
}