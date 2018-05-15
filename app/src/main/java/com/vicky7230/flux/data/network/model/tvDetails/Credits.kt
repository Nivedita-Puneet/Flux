package com.vicky7230.flux.data.network.model.tvDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Credits(

        @SerializedName("cast")
        @Expose
        var cast: List<Cast>? = null,
        @SerializedName("crew")
        @Expose
        var crew: List<Crew>? = null

)
