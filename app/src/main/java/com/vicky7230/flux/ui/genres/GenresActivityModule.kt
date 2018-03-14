package com.vicky7230.flux.ui.genres

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.vicky7230.flux.di.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Created by vicky on 28/2/18.
 */
@Module
class GenresActivityModule {

    @Provides
    fun provideHomeMvpPresenter(presenter: GenresPresenter<GenresMvpView>): GenresMvpPresenter<GenresMvpView> {
        return presenter
    }

    @Provides
    fun provideGenresAdapter(): GenresAdapter {
        return GenresAdapter(ArrayList())
    }

    @Provides
    fun provideLinearLayoutManager(@ApplicationContext context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}