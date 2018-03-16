package com.vicky7230.flux.ui.login

import com.vicky7230.flux.ui.base.MvpView

/**
 * Created by vicky on 16/3/18.
 */
interface LoginMvpView : MvpView {
    fun launchBrowserForLogin(requestToken: String?)

}