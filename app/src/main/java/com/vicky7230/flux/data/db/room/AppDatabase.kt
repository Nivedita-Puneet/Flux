package com.vicky7230.cayennekt.data.db.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.vicky7230.flux.data.db.room.model.ConfigurationDbModel
import com.vicky7230.flux.data.db.room.ConfigurationDao

/**
 * Created by vicky on 31/12/17.
 */
@Database(entities = [ConfigurationDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun configurationDao(): ConfigurationDao
}