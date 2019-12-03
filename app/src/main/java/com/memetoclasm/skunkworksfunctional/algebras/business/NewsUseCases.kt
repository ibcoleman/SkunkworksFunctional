package com.memetoclasm.skunkworksfunctional.algebras.business

import arrow.Kind
import com.memetoclasm.skunkworksfunctional.algebras.business.model.NewsItem
import com.memetoclasm.skunkworksfunctional.algebras.data.network.CachePolicy
import com.memetoclasm.skunkworksfunctional.algebras.data.network.getNewsItemDetailsWithCachePolicy
import com.memetoclasm.skunkworksfunctional.algebras.data.network.getNewsWithCachePolicy
import com.memetoclasm.skunkworksfunctional.runtime.context.Runtime


fun <F> Runtime<F>.getNews(): Kind<F, List<NewsItem>> =
    getNewsWithCachePolicy(CachePolicy.NetworkOnly)

fun <F> Runtime<F>.getNewsItemDetails(title: String): Kind<F, NewsItem> =
    getNewsItemDetailsWithCachePolicy(CachePolicy.NetworkOnly, title)