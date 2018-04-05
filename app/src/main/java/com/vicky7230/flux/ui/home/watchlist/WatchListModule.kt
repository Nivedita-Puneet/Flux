package com.vicky7230.flux.ui.home.watchlist

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import com.vicky7230.flux.R
import com.vicky7230.flux.di.ApplicationContext
import com.vicky7230.flux.ui.home.tv.ItemOffsetDecoration
import dagger.Module
import dagger.Provides

@Module
class WatchListModule {
    @Provides
    fun provideWatchListMvpPresenter(presenter: WatchListPresenter<WatchListMvpView>): WatchListMvpPresenter<WatchListMvpView> {
        return presenter
    }

    @Provides
    fun provideGridLayoutManager(@ApplicationContext context: Context): GridLayoutManager {
        return GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
    }

    @Provides
    fun provideItemOffsetDecoration(@ApplicationContext context: Context): ItemOffsetDecoration {
        return ItemOffsetDecoration( context.resources.getDimensionPixelOffset(R.dimen.tvImageOffset))
    }

    @Provides
    fun provideRecipesAdapter(): WatchListAdapter {
        return WatchListAdapter(ArrayList())
    }
}