package com.vicky7230.flux.ui.home.discover

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.vicky7230.flux.di.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Created by vicky on 15/3/18.
 */
@Module
class DiscoverModule {
    @Provides
    fun provideDiscoverMvpPresenter(presenter: DiscoverPresenter<DiscoverMvpView>): DiscoverMvpPresenter<DiscoverMvpView> {
        return presenter
    }

    @Provides
    fun provideGridLayoutManager(@ApplicationContext context: Context): GridLayoutManager {
        return GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
    }

    @Provides
    fun provideDiscoverItemOffsetDecoration(): DiscoverItemOffsetDecoration {
        return DiscoverItemOffsetDecoration(15)
    }

    @Provides
    fun provideGenresAdapter(): DiscoverAdapter {
        return DiscoverAdapter(ArrayList())
    }
}