package com.vicky7230.flux.ui.tvDetails

import com.vicky7230.flux.ui.tvDetails.info.InfoFragment
import com.vicky7230.flux.ui.tvDetails.info.InfoModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by vicky on 24/3/18.
 */
@Module
abstract class TvDetailsFragmenProvider {

    @ContributesAndroidInjector(modules = [(InfoModule::class)])
    internal abstract fun provideInfoFragmentFactory(): InfoFragment
}