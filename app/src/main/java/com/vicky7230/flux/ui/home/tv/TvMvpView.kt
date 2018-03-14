package com.vicky7230.flux.ui.home.tv

import com.vicky7230.flux.data.network.model.results.Result
import com.vicky7230.flux.ui.base.MvpView

/**
 * Created by vicky on 27/2/18.
 */
interface TvMvpView : MvpView {

    fun updateTvList(torrents: MutableList<Result>)
}