package com.vicky7230.flux.ui.tvDetails.info

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.vicky7230.flux.di.ApplicationContext
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

    @Provides
    fun provideLinearLayoutManager(@ApplicationContext context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    @Provides
    fun provideCastListAdapter(): CastListAdapter {
        return CastListAdapter(arrayListOf())
    }
}