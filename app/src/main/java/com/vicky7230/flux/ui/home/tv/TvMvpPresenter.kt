package com.vicky7230.flux.ui.home.tv

import com.vicky7230.flux.ui.base.MvpPresenter

/**
 * Created by vicky on 27/2/18.
 */
interface TvMvpPresenter<V : TvMvpView> : MvpPresenter<V> {
    fun getTvs(sortBy: String, ratingMoreThan: Int, discoverGenreId: String?)
    fun resetPageVariable()
}