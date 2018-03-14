package com.vicky7230.flux.ui.home

import dagger.Module
import dagger.Provides


/**
 * Created by vicky on 12/2/18.
 */
@Module
class HomeActivityModule {

    @Provides
    fun provideHomeMvpPresenter(presenter: HomePresenter<HomeMvpView>): HomeMvpPresenter<HomeMvpView> {
        return presenter
    }
}