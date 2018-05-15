package com.vicky7230.flux.ui.genres

import com.vicky7230.flux.data.network.model.genres.Genre
import com.vicky7230.flux.ui.base.MvpView

/**
 * Created by vicky on 28/2/18.
 */
interface GenresMvpView : MvpView {
    fun showGenres(genres: MutableList<Genre>?)
    fun goToHome()
}