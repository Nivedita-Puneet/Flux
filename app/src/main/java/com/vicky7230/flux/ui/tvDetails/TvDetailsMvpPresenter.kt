package com.vicky7230.flux.ui.tvDetails

import com.vicky7230.flux.ui.base.MvpPresenter

/**
 * Created by vicky on 21/3/18.
 */
interface TvDetailsMvpPresenter<V : TvDetailsMvpView> : MvpPresenter<V> {
    fun getTvDetails(tvId: String)
    fun addToFavourites(tvId: String)
    fun addToWatchList(tvId: String)
}