package com.memetoclasm.skunkworksfunctional.algebras.data.network

import arrow.Kind
import com.memetoclasm.skunkworksfunctional.algebras.business.model.NewsItem
import com.memetoclasm.skunkworksfunctional.runtime.context.Runtime

sealed class CachePolicy {
    object NetworkOnly : CachePolicy()
    object NetworkFirst : CachePolicy()
    object LocalOnly : CachePolicy()
    object LocalFirst : CachePolicy()
}

fun <F> Runtime<F>.getNewsWithCachePolicy(policy: CachePolicy): Kind<F, List<NewsItem>> =
    when (policy) {
        CachePolicy.NetworkOnly -> loadNews()
        CachePolicy.NetworkFirst -> loadNews() // TODO change to conditional call
        CachePolicy.LocalOnly -> loadNews() // TODO change to local only cache call
        CachePolicy.LocalFirst -> loadNews() // TODO change to conditional call
    }

fun <F> Runtime<F>.getNewsItemDetailsWithCachePolicy(policy: CachePolicy, title: String): Kind<F, NewsItem> =
    when (policy) {
        CachePolicy.NetworkOnly -> loadNewsItemDetails(title)
        CachePolicy.NetworkFirst -> loadNewsItemDetails(title) // TODO change to conditional call
        CachePolicy.LocalOnly -> loadNewsItemDetails(title) // TODO change to local only cache call
        CachePolicy.LocalFirst -> loadNewsItemDetails(title) // TODO change to conditional call
    }