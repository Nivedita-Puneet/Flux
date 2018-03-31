package com.vicky7230.flux.di.module

import com.vicky7230.flux.ui.genres.GenresActivity
import com.vicky7230.flux.ui.genres.GenresActivityModule
import com.vicky7230.flux.ui.home.FragmentProvider
import com.vicky7230.flux.ui.home.HomeActivity
import com.vicky7230.flux.ui.home.HomeActivityModule
import com.vicky7230.flux.ui.login.LoginActivity
import com.vicky7230.flux.ui.login.LoginActivityModule
import com.vicky7230.flux.ui.search.SearchActivity
import com.vicky7230.flux.ui.search.SearchActivityModule
import com.vicky7230.flux.ui.splash.SplashActivity
import com.vicky7230.flux.ui.splash.SplashActivityModule
import com.vicky7230.flux.ui.tvDetails.TvDetailsActivity
import com.vicky7230.flux.ui.tvDetails.TvDetailsActivityModule
import com.vicky7230.flux.ui.tvDetails.TvDetailsFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by vicky on 1/1/18.
 */
@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [(SplashActivityModule::class)])
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [(GenresActivityModule::class)])
    abstract fun bindGenresActivity(): GenresActivity

    @ContributesAndroidInjector(modules = [(HomeActivityModule::class), (FragmentProvider::class)])
    abstract fun bindHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [(LoginActivityModule::class)])
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [(TvDetailsActivityModule::class), (TvDetailsFragmentProvider::class)])
    abstract fun bindTvDetailsActivity(): TvDetailsActivity

    @ContributesAndroidInjector(modules = [(SearchActivityModule::class)])
    abstract fun bindSearchActivity(): SearchActivity

}