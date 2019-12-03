package com.memetoclasm.skunkworksfunctional.algebras.ui.model

data class NewsItemViewState(
    val title: String,
    val author: String?,
    val photoUrl: String?,
    val publishedAt: String,
    val description: String?,
    val content: String)
