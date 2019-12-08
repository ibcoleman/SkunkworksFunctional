package com.memetoclasm.skunkworksfunctional.algebras.data.network.dto

import com.squareup.moshi.JsonClass

/**
 * A response returned from the REST request via Retrofit.
 *
 * By Ian Coleman <ian.coleman@tacticaledge.us> on 12/3/19.
 */
@JsonClass(generateAdapter = true)
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NetworkNewsItem>
)