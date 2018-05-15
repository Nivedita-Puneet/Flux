package com.vicky7230.flux.data.db

import com.vicky7230.cayennekt.data.db.room.AppDatabase
import com.vicky7230.flux.data.db.room.model.ConfigurationDbModel
import javax.inject.Inject

/**
 * Created by vicky on 31/12/17.
 */
class AppDbHelper @Inject
constructor(private val appDatabase: AppDatabase) : DbHelper {
    override fun deleteConfigurations() {
        appDatabase.configurationDao().deleteConfigurations()
    }

    override fun insertConfigurations(configurations: MutableList<ConfigurationDbModel>): List<Long>  {
        return appDatabase.configurationDao().insertConfigurations(configurations)
    }

}