package com.vicky7230.flux.ui.search

import com.vicky7230.flux.ui.base.MvpPresenter

interface SearchMvpPresenter<V : SearchMvpView> : MvpPresenter<V> {
    fun getSearchResults(query: String)
    fun resetPageVariable()

}
