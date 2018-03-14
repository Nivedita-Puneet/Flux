package com.vicky7230.flux.data.db.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.vicky7230.flux.data.db.room.model.ConfigurationDbModel

/**
 * Created by vicky on 1/3/18.
 */
@Dao
interface ConfigurationDao {

    @Query("DELETE FROM configuration")
    fun deleteConfigurations()

    @Insert
    fun insertConfigurations(configurations: MutableList<ConfigurationDbModel>): List<Long>

}