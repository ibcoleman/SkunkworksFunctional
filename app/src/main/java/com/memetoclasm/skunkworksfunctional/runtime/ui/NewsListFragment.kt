package com.memetoclasm.skunkworksfunctional.runtime.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import arrow.fx.IO
import arrow.fx.extensions.io.unsafeRun.runNonBlocking
import arrow.unsafe
import com.google.android.material.snackbar.Snackbar
import com.memetoclasm.skunkworksfunctional.R
import com.memetoclasm.skunkworksfunctional.algebras.ui.NewsListView
import com.memetoclasm.skunkworksfunctional.algebras.ui.adapter.NewsRecyclerAdapter
import com.memetoclasm.skunkworksfunctional.algebras.ui.getAllNews
import com.memetoclasm.skunkworksfunctional.algebras.ui.model.NewsItemViewState
import com.memetoclasm.skunkworksfunctional.runtime.application
import com.memetoclasm.skunkworksfunctional.runtime.context.runtime
import kotlinx.android.synthetic.main.fragment_news_list.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NewsListFragment : Fragment(), NewsListView {

    private lateinit var adapter: NewsRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        newsList.setHasFixedSize(true)
        newsList.layoutManager = LinearLayoutManager(context)
        adapter = NewsRecyclerAdapter(itemClick = newsItemClickHandler)
        newsList.adapter = adapter

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            val action =
                NewsListFragmentDirections.actionFirstFragmentToSecondFragment(
                    //ToDo: Add news detail id here
                    "From NewsListFragment"
                )
            findNavController().navigate(action)
        }
    }

    private val newsItemClickHandler : (NewsItemViewState) -> Unit = {
        val action = NewsListFragmentDirections.actionFirstFragmentToSecondFragment(
            it.title
            )
        findNavController().navigate(action)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()

        context?.let { ctx ->
            unsafe {
                runNonBlocking({
                    IO.runtime(ctx.application().runtimeContext)
                        .getAllNews(this@NewsListFragment)
                }, {})
            }
        }
    }

    override fun showLoading() {
        loader.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loader.visibility = View.GONE
    }

    override fun drawNews(news: List<NewsItemViewState>) {
        adapter.news = news
        adapter.notifyDataSetChanged()
    }

    override fun showNotFoundError() {
        Snackbar.make(newsList, R.string.not_found, Snackbar.LENGTH_SHORT).show()
    }

    override fun showGenericError() {
        Snackbar.make(newsList, R.string.generic, Snackbar.LENGTH_SHORT).show()
    }

    override fun showAuthenticationError() {
        Snackbar.make(newsList, R.string.authentication, Snackbar.LENGTH_SHORT).show()
    }

    override fun showServerError() {
        Snackbar.make(newsList, R.string.server, Snackbar.LENGTH_SHORT).show()
    }
}
