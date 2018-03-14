package com.vicky7230.flux.di.component

import com.vicky7230.flux.di.module.NetworkModule
import com.vicky7230.flux.EZSeriesApplication
import com.vicky7230.flux.di.module.ActivityBindingModule
import com.vicky7230.flux.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Created by vicky on 12/2/18.
 */
@Singleton
@Component(
    modules =
    [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ApplicationModule::class,
        ActivityBindingModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(ezSeriesApplication: EZSeriesApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(ezSeriesApplication: EZSeriesApplication)

}