package com.vicky7230.flux.ui.home

import com.vicky7230.flux.ui.home.discover.DiscoverFragment
import com.vicky7230.flux.ui.home.discover.DiscoverModule
import com.vicky7230.flux.ui.home.tv.TvFragment
import com.vicky7230.flux.ui.home.tv.TvModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by vicky on 11/2/18.
 */
@Module
abstract class FragmentProvider {

    @ContributesAndroidInjector(modules = [(TvModule::class)])
    internal abstract fun provideTvFragmentFactory(): TvFragment

    @ContributesAndroidInjector(modules = [(DiscoverModule::class)])
    internal abstract fun provideDiscoverFragmentFactory(): DiscoverFragment

}