package com.vicky7230.flux.ui.home.profile

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

}