package com.vicky7230.flux.data.network.model.genres

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Genre(

    var selected: Boolean = false,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null

)
