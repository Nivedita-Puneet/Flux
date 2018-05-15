package com.vicky7230.flux.data.db.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by vicky on 1/3/18.
 */
@Entity(tableName = "configuration")
data class ConfigurationDbModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "value")
    var value: String
)