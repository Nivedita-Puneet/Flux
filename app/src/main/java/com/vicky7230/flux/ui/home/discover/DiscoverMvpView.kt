package com.vicky7230.flux.ui.home.discover

import com.vicky7230.flux.data.network.model.genres.Genre
import com.vicky7230.flux.ui.base.MvpView

/**
 * Created by vicky on 15/3/18.
 */
interface DiscoverMvpView : MvpView {
    fun showGenres(genres: MutableList<Genre>)

}