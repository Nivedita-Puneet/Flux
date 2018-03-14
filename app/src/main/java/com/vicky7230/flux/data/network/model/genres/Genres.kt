package com.vicky7230.flux.data.network.model.genres

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Genres(

    @SerializedName("genres")
    @Expose
    var genres: MutableList<Genre>? = null

)
