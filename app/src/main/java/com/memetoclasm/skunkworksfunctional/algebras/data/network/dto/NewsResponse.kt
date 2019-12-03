package com.memetoclasm.skunkworksfunctional.algebras.data.network.dto

/**
 * A response returned from the REST request via Retrofit.
 *
 * By Ian Coleman <ian.coleman@tacticaledge.us> on 12/3/19.
 */
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NetworkNewsItem>
)