package com.vicky7230.flux.ui.login

import com.vicky7230.flux.ui.base.MvpPresenter

/**
 * Created by vicky on 16/3/18.
 */
interface LoginMvpPresenter<V : LoginMvpView> : MvpPresenter<V> {
    fun requestAuthenticationToken()
    fun getSessionId(requestToken: String?)

}