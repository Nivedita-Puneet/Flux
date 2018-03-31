package com.vicky7230.flux.ui.home.tv

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import com.vicky7230.flux.R
import com.vicky7230.flux.di.ApplicationContext
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
    fun provideItemOffsetDecoration(@ApplicationContext context: Context): ItemOffsetDecoration {
        return ItemOffsetDecoration( context.resources.getDimensionPixelOffset(R.dimen.tvImageOffset))
    }

    @Provides
    fun provideRecipesAdapter(): TvAdapter {
        return TvAdapter(ArrayList())
    }
}