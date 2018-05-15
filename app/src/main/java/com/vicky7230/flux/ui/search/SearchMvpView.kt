package com.vicky7230.flux.ui.search

import com.vicky7230.flux.data.network.model.results.Result
import com.vicky7230.flux.ui.base.MvpView

interface SearchMvpView : MvpView {
    fun updateSearchList(results: MutableList<Result>)

}
