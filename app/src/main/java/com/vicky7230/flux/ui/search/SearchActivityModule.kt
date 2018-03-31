package com.vicky7230.flux.ui.search

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.vicky7230.flux.R
import com.vicky7230.flux.di.ApplicationContext
import com.vicky7230.flux.ui.home.tv.ItemOffsetDecoration
import dagger.Module
import dagger.Provides

@Module
class SearchActivityModule {

    @Provides
    fun provideSearchMvpPresenter(presenter: SearchPresenter<SearchMvpView>): SearchMvpPresenter<SearchMvpView> {
        return presenter
    }

    @Provides
    fun provideLinearLayoutManager(@ApplicationContext context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    @Provides
    fun provideItemOffsetDecoration(@ApplicationContext context: Context): ItemOffsetDecoration {
        return ItemOffsetDecoration( context.resources.getDimensionPixelOffset(R.dimen.tvImageOffset))
    }

    @Provides
    fun provideTvSearchAdapter(): TvSearchAdapter {
        return TvSearchAdapter(arrayListOf())
    }
}