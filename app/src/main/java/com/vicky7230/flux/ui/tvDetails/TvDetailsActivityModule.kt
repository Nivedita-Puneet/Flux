package com.vicky7230.flux.ui.tvDetails

import dagger.Module
import dagger.Provides

/**
 * Created by vicky on 21/3/18.
 */

@Module
class TvDetailsActivityModule {

    @Provides
    fun provideTvDetailsMvpPresenter(presenter: TvDetailsPresenter<TvDetailsMvpView>): TvDetailsMvpPresenter<TvDetailsMvpView> {
        return presenter
    }

    @Provides
    fun provideDetailsPagerAdapter(tvDetailsActivity: TvDetailsActivity): DetailsPagerAdapter {
        return DetailsPagerAdapter(tvDetailsActivity.supportFragmentManager, arrayListOf())
    }
}