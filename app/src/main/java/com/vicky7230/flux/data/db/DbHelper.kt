package com.vicky7230.flux.data.db

import com.vicky7230.flux.data.db.room.model.ConfigurationDbModel

/**
 * Created by vicky on 31/12/17.
 */
interface DbHelper {
    fun deleteConfigurations()

    fun insertConfigurations(configurations: MutableList<ConfigurationDbModel>): List<Long>
}