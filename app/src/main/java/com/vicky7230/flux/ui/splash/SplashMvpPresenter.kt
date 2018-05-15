package com.vicky7230.flux.ui.splash

import com.vicky7230.flux.ui.base.MvpPresenter

/**
 * Created by vicky on 1/3/18.
 */
interface SplashMvpPresenter<V : SplashMvpView> : MvpPresenter<V> {
    fun getConfigurations()
}