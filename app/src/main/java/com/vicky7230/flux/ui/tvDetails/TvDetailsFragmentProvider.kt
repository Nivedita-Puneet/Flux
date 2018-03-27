package com.vicky7230.flux.ui.tvDetails

import com.vicky7230.flux.ui.tvDetails.info.InfoFragment
import com.vicky7230.flux.ui.tvDetails.info.InfoModule
import com.vicky7230.flux.ui.tvDetails.reviews.ReviewsFragment
import com.vicky7230.flux.ui.tvDetails.reviews.ReviewsModule
import com.vicky7230.flux.ui.tvDetails.seasons.SeasonsFragment
import com.vicky7230.flux.ui.tvDetails.seasons.SeasonsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by vicky on 24/3/18.
 */
@Module
abstract class TvDetailsFragmentProvider {

    @ContributesAndroidInjector(modules = [(InfoModule::class)])
    internal abstract fun provideInfoFragmentFactory(): InfoFragment

    @ContributesAndroidInjector(modules = [(ReviewsModule::class)])
    internal abstract fun provideReviewsFragmentFactory(): ReviewsFragment

    @ContributesAndroidInjector(modules = [(SeasonsModule::class)])
    internal abstract fun provideSeasonsFragmentFactory(): SeasonsFragment
}