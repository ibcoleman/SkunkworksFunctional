package com.memetoclasm.skunkworksfunctional.algebras.ui

import arrow.Kind
import com.memetoclasm.skunkworksfunctional.algebras.business.getNews
import com.memetoclasm.skunkworksfunctional.algebras.business.model.DomainError
import com.memetoclasm.skunkworksfunctional.algebras.business.model.NewsItem
import com.memetoclasm.skunkworksfunctional.algebras.ui.model.NewsItemViewState
import com.memetoclasm.skunkworksfunctional.runtime.context.Runtime


interface NewsView {

    fun showLoading(): Unit

    fun hideLoading(): Unit

    fun showNotFoundError(): Unit

    fun showGenericError(): Unit

    fun showAuthenticationError(): Unit

    fun showServerError(): Unit
}

interface NewsListView : NewsView {

    fun drawNews(news: List<NewsItemViewState>): Unit
}

interface NewsItemDetailView : NewsView {

    fun drawNewsItem(newsItem: NewsItemViewState)
}

private fun displayErrors(
    view: NewsView,
    t: Throwable
): Unit {
    when (DomainError.fromThrowable(t)) {
        is DomainError.NotFoundError -> view.showNotFoundError()
        is DomainError.UnknownServerError -> view.showGenericError()
        is DomainError.AuthenticationError -> view.showAuthenticationError()
        is DomainError.ServerError -> view.showServerError()
    }
}

fun <F> Runtime<F>.getAllNews(view: NewsListView): Kind<F, Unit> = fx.concurrent {
    !effect { view.showLoading() }
    val maybeNews = !getNews().attempt()
    !effect { view.hideLoading() }
    !effect {
        maybeNews.fold(
            ifLeft = { displayErrors(view, it) },
            ifRight = { view.drawNews(it.map { newsItem -> newsItem.toViewState() }) }
        )
    }
}

fun NewsItem.toViewState() = NewsItemViewState(
    title = title,
    author = author,
    photoUrl = urlToImage,
    publishedAt = publishedAt,
    description = description,
    content = content
)