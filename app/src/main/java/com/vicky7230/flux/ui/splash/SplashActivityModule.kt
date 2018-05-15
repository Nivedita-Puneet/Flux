package com.vicky7230.flux.ui.splash

import dagger.Module
import dagger.Provides

/**
 * Created by vicky on 28/2/18.
 */
@Module
class SplashActivityModule {

    @Provides
    fun provideHomeMvpPresenter(presenter: SplashPresenter<SplashMvpView>): SplashMvpPresenter<SplashMvpView> {
        return presenter
    }
}