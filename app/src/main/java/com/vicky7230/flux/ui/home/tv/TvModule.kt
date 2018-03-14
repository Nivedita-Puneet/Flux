package com.vicky7230.flux.ui.home.tv

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import com.vicky7230.flux.di.ApplicationContext
import com.vicky7230.flux.ui.home.ItemOffsetDecoration
import dagger.Module
import dagger.Provides

/**
 * Created by vicky on 27/2/18.
 */
@Module
class TvModule {
    @Provides
    fun provideTvMvpPresenter(presenter: TvPresenter<TvMvpView>): TvMvpPresenter<TvMvpView> {
        return presenter
    }

    @Provides
    fun provideGridLayoutManager(@ApplicationContext context: Context): GridLayoutManager {
        return GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
    }

    @Provides
    fun provideItemOffsetDecoration(): ItemOffsetDecoration {
        return ItemOffsetDecoration(7)
    }

    @Provides
    fun provideRecipesAdapter(): TvAdapter {
        return TvAdapter(ArrayList())
    }
}