package com.vicky7230.flux.ui.home.discover

import com.vicky7230.flux.ui.base.MvpPresenter

/**
 * Created by vicky on 15/3/18.
 */
interface DiscoverMvpPresenter<V : DiscoverMvpView> : MvpPresenter<V> {
    fun getGenresList()
}