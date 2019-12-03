package com.memetoclasm.skunkworksfunctional.algebras.data.network

import arrow.Kind
import arrow.core.toOption
import com.memetoclasm.skunkworksfunctional.algebras.business.model.NewsItem
import com.memetoclasm.skunkworksfunctional.algebras.data.network.dto.NewsResponse
import com.memetoclasm.skunkworksfunctional.algebras.data.network.error.NetworkError
import com.memetoclasm.skunkworksfunctional.algebras.data.network.mapper.normalizeError
import com.memetoclasm.skunkworksfunctional.algebras.data.network.mapper.toDomain
import com.memetoclasm.skunkworksfunctional.algebras.data.network.mapper.toNetworkError
import retrofit2.Response
import com.memetoclasm.skunkworksfunctional.runtime.context.Runtime

/**
 *
 */
fun <F> Runtime<F>.loadNews(): Kind<F, List<NewsItem>> = fx.concurrent {
    val response = !effect(context.bgDispatcher) { fetchNews() }
    continueOn(context.mainDispatcher)

    if (response.isSuccessful) {
        response.news().toDomain()
    } else {
        !raiseError<List<NewsItem>>(response.code().toNetworkError())
    }
}.handleErrorWith { error -> raiseError(error.normalizeError()) }

/**
 *
 */
fun <F> Runtime<F>.loadNewsItemDetails(title: String): Kind<F, NewsItem> = fx.concurrent {
    val response = !effect(context.bgDispatcher) { fetchNews() }
    continueOn(context.mainDispatcher)

    if (response.isSuccessful) {
        response.news().find { it.title == title }?.toDomain().toOption().fold(
            ifEmpty = { !raiseError<NewsItem>(NetworkError.NotFound) },
            ifSome = { it }
        )
    } else {
        !raiseError<NewsItem>(response.code().toNetworkError())
    }
}.handleErrorWith { error -> raiseError(error.normalizeError()) }

/**
 *
 */
private fun <F> Runtime<F>.fetchNews() = context.newsService.fetchNews("android").execute()

private fun Response<NewsResponse>.news() = body()!!.articles