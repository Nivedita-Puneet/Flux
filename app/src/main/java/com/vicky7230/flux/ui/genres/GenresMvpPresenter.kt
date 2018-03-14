package com.vicky7230.flux.ui.genres

import com.vicky7230.flux.ui.base.MvpPresenter

/**
 * Created by vicky on 28/2/18.
 */
interface GenresMvpPresenter<V : GenresMvpView> : MvpPresenter<V> {
    fun getGenres()
    fun saveGenres(genreIds: String)
}