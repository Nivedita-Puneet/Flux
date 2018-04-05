package com.vicky7230.flux.ui.home.watchlist

import com.vicky7230.flux.data.network.model.results.Result
import com.vicky7230.flux.ui.base.MvpView

interface WatchListMvpView : MvpView {
    fun showWatchList(results: MutableList<Result>)

}
