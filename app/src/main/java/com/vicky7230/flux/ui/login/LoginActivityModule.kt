package com.vicky7230.flux.ui.login

import dagger.Module
import dagger.Provides

/**
 * Created by vicky on 16/3/18.
 */
@Module
class LoginActivityModule {

    @Provides
    fun provideLoginMvpPresenter(presenter: LoginPresenter<LoginMvpView>): LoginMvpPresenter<LoginMvpView> {
        return presenter
    }

}