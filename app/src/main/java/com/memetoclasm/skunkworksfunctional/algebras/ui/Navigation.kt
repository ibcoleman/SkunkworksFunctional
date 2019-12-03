package com.memetoclasm.skunkworksfunctional.algebras.ui

import android.content.Context
import androidx.navigation.NavController
import arrow.Kind
import com.memetoclasm.skunkworksfunctional.runtime.context.Runtime
import com.memetoclasm.skunkworksfunctional.runtime.ui.NewsListFragmentDirections

/**
 *
 */
fun <F> Runtime<F>.goToNewsItemDetail(navController: NavController,  title: String): Kind<F, Unit> = fx.concurrent {
    !effect {
        navController.navigate(
            NewsListFragmentDirections.actionFirstFragmentToSecondFragment(title)
        )
    }
}