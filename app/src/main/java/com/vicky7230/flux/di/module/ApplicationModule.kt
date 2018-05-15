package com.vicky7230.flux.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import com.vicky7230.cayennekt.data.db.room.AppDatabase
import com.vicky7230.flux.EZSeriesApplication
import com.vicky7230.flux.R
import com.vicky7230.flux.data.AppDataManager
import com.vicky7230.flux.data.Config
import com.vicky7230.flux.data.DataManager
import com.vicky7230.flux.data.network.ApiHelper
import com.vicky7230.flux.data.network.AppApiHelper
import com.vicky7230.flux.data.prefs.AppPreferencesHelper
import com.vicky7230.flux.data.prefs.PreferencesHelper
import com.vicky7230.flux.di.ApplicationContext
import com.vicky7230.flux.di.BaseUrl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by vicky on 31/12/17.
 */
@Module
class ApplicationModule {

    @Provides
    @ApplicationContext
    internal fun provideContext(ezSeriesApplication: EZSeriesApplication): Context {
        return ezSeriesApplication.applicationContext
    }

    @Provides
    internal fun provideApplication(ezSeriesApplication: EZSeriesApplication): Application {
        return ezSeriesApplication
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideCustomTabsIntent(@ApplicationContext context: Context): CustomTabsIntent {
        return CustomTabsIntent.Builder()
            .setShowTitle(true)
            .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
            .setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
            .addDefaultShareMenuItem()
            .build()
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, Config.DB_NAME).build()
    }

    @Provides
    @BaseUrl
    internal fun provideBaseUrl(): String {
        return Config.BASE_URL
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    /*@Provides
    @Singleton
    internal fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }*/

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

    @Provides
    @Singleton
    internal fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferencesHelper
    }
}