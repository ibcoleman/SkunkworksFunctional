package com.memetoclasm.skunkworksfunctional.algebras.data.network.dto

/**
 * This is a placeholder description of this class.
 *
 * By Ian Coleman <ian.coleman@tacticaledge.us> on 12/3/19.
 */
data class NetworkNewsItem(
    val source: NetworkNewsSource,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String
)

data class NetworkNewsSource(
    val id: String?,
    val name: String
)