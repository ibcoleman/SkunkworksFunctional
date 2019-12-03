package com.memetoclasm.skunkworksfunctional.runtime.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.memetoclasm.skunkworksfunctional.R
import com.memetoclasm.skunkworksfunctional.algebras.ui.NewsListView
import com.memetoclasm.skunkworksfunctional.algebras.ui.adapter.NewsRecyclerAdapter
import com.memetoclasm.skunkworksfunctional.algebras.ui.model.NewsItemViewState
import kotlinx.android.synthetic.main.fragment_news_list.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NewsListFragment : Fragment(), NewsListView {

    private lateinit var adapter: NewsRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            val action =
                NewsListFragmentDirections.actionFirstFragmentToSecondFragment(
                    //ToDo: Add news detail id here
                    "From NewsListFragment"
                )
            findNavController().navigate(action)
        }
    }

    //ToDo: initialize the list
    override fun onResume() {
        super.onResume()

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
}
