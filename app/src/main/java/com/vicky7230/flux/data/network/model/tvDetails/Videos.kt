package com.vicky7230.flux.data.network.model.tvDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Videos(

        @SerializedName("id")
        @Expose
        var id: Int? = null,
        @SerializedName("results")
        @Expose
        var results: List<Result>? = null
)
