package com.vicky7230.flux.ui.home.profile

import com.vicky7230.flux.ui.base.MvpPresenter

/**
 * Created by vicky on 18/3/18.
 */
interface ProfileMvpPresenter<V : ProfileMvpView> : MvpPresenter<V> {
    fun getAccountDetails()
    fun getFavourites()
}