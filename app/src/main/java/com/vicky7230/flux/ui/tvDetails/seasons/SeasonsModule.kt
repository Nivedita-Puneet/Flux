package com.vicky7230.flux.ui.tvDetails.seasons

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.vicky7230.flux.di.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class SeasonsModule {

    @Provides
    fun provideSeasonsMvpPresenter(presenter: SeasonsPresenter<SeasonsMvpView>): SeasonsMvpPresenter<SeasonsMvpView> {
        return presenter
    }

    @Provides
    fun provideLinearLayoutManager(@ApplicationContext context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    @Provides
    fun provideSeasonAdapter(): SeasonsAdapter {
        return SeasonsAdapter(arrayListOf())
    }
}
