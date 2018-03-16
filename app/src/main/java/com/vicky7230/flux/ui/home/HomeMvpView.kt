package com.vicky7230.flux.ui.home

import com.vicky7230.flux.ui.base.MvpView

/**
 * Created by vicky on 11/2/18.
 */
interface HomeMvpView : MvpView {
    fun changeFragment(fragment: String)
    fun showLoginScreen()
}