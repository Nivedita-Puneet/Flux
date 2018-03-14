package com.vicky7230.flux.ui.home.discover

import android.content.Context
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
    fun provideLinearLayoutManager(@ApplicationContext context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    @Provides
    fun provideDiscoverItemOffsetDecoration(): DiscoverItemOffsetDecoration {
        return DiscoverItemOffsetDecoration(20)
    }

    @Provides
    fun provideGenresAdapter(): DiscoverAdapter {
        return DiscoverAdapter(ArrayList())
    }
}