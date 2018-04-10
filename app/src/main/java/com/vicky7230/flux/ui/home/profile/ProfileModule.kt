package com.vicky7230.flux.ui.home.profile

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.vicky7230.flux.R
import com.vicky7230.flux.di.ApplicationContext
import com.vicky7230.flux.ui.home.tv.ItemOffsetDecoration
import dagger.Module
import dagger.Provides

/**
 * Created by vicky on 18/3/18.
 */
@Module
class ProfileModule {

    @Provides
    fun provideProfileMvpPresenter(presenter: ProfilePresenter<ProfileMvpView>): ProfileMvpPresenter<ProfileMvpView> {
        return presenter
    }

    @Provides
    fun provideLinearLayoutManager(@ApplicationContext context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    @Provides
    fun provideTvSearchAdapter(): FavouriteAdapter {
        return FavouriteAdapter(arrayListOf())
    }
}