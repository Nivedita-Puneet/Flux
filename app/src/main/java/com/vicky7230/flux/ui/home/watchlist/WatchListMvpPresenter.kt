package com.vicky7230.flux.ui.home.watchlist

import com.vicky7230.flux.ui.base.MvpPresenter

interface WatchListMvpPresenter<V : WatchListMvpView> : MvpPresenter<V> {

    fun getWatchList()

    fun resetPageVariable()
}