package com.vicky7230.flux.ui.home.watchlist


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.vicky7230.flux.R
import com.vicky7230.flux.data.network.model.results.Result
import com.vicky7230.flux.ui.base.BaseFragment
import com.vicky7230.flux.ui.home.LoginSuccessfulEventGetWatchlist
import com.vicky7230.flux.ui.home.tv.ItemOffsetDecoration
import com.vicky7230.flux.ui.tvDetails.TvDetailsActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_watchlist.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class WatchListFragment : BaseFragment(), WatchListMvpView, WatchListAdapter.Callback {

    @Inject
    lateinit var presenter: WatchListMvpPresenter<WatchListMvpView>
    @Inject
    lateinit var gridLayoutManager: GridLayoutManager
    @Inject
    lateinit var itemOffsetDecoration: ItemOffsetDecoration
    @Inject
    lateinit var watchListAdapter: WatchListAdapter

    var isLoading = false

    companion object {
        fun newInstance() = WatchListFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_watchlist, container, false)
        presenter.onAttach(this)
        watchListAdapter.setCallback(this)
        return view
    }

    override fun setUp(view: View) {

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (watchListAdapter.getItem(position)?.type) {
                    "LOADING" -> 2
                    "RESULT" -> 1
                    else -> 1
                }
            }
        }

        watch_list.layoutManager = gridLayoutManager
        watch_list.addItemDecoration(itemOffsetDecoration)
        watch_list.adapter = watchListAdapter

        watch_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                //super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = gridLayoutManager.childCount
                val totalItemCount = gridLayoutManager.itemCount
                val pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading) {
                    watchListAdapter.addItem(
                            Result(
                                    type = "LOADING"
                            )
                    )
                    presenter.getWatchList()
                    isLoading = true
                }
            }
        })

        presenter.getWatchList()

    }

    override fun showWatchList(results: MutableList<Result>) {
        watchlist_progress.visibility = GONE
        watch_list.visibility = VISIBLE
        if (watchListAdapter.itemCount > 0)
            watchListAdapter.removeItem()
        if (results.size > 0) {
            watchListAdapter.addItems(results)
            isLoading = false
        }
    }

    override fun onTvShowClick(id: Int) {
        startActivity(TvDetailsActivity.getStartIntent(activity as Context, id.toString()))
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onLoginSuccessfulEvent(eventGetWatchlist: LoginSuccessfulEventGetWatchlist) {
        val loginSuccessfulEventGetWatchlist = EventBus.getDefault().getStickyEvent(LoginSuccessfulEventGetWatchlist::class.java)
        if (loginSuccessfulEventGetWatchlist != null) {
            showMessage("Got Watchlist Event.")
            EventBus.getDefault().removeStickyEvent(loginSuccessfulEventGetWatchlist)
            watchlist_progress.visibility = VISIBLE
            watch_list.visibility = GONE
            watchListAdapter.clearItems()
            presenter.resetPageVariable()
            presenter.getWatchList()
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

}// Required empty public constructor
