package com.memetoclasm.skunkworksfunctional.runtime.context

import arrow.fx.ForIO
import arrow.fx.IO
import arrow.fx.extensions.io.concurrent.concurrent
import arrow.fx.typeclasses.Concurrent
import com.memetoclasm.skunkworksfunctional.algebras.data.network.NewsApiService
import kotlinx.coroutines.CoroutineDispatcher

/**
 * This is the functional "context" which we pass in to the system for evaluation.
 *
 * By Ian Coleman <ian.coleman@tacticaledge.us> on 12/3/19.
 */
@Suppress("DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE")
abstract class Runtime<F>(concurrent: Concurrent<F>, val context: RuntimeContext) : Concurrent<F> by concurrent

fun IO.Companion.runtime(ctx: RuntimeContext) = object : Runtime<ForIO>(IO.concurrent(), ctx) {}

data class RuntimeContext(
    val bgDispatcher: CoroutineDispatcher,
    val mainDispatcher: CoroutineDispatcher,
    val newsService: NewsApiService
)
