package com.vicky7230.flux.ui.tvDetails.info

import dagger.Module
import dagger.Provides

/**
 * Created by vicky on 24/3/18.
 */
@Module
class InfoModule {

    @Provides
    fun provideInfoMvpPresenter(presenter: InfoPresenter<InfoMvpView>): InfoMvpPresenter<InfoMvpView> {
        return presenter
    }

}