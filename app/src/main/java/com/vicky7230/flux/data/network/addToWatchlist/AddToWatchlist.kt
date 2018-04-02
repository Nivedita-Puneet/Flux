package com.vicky7230.flux.data.network.addToWatchlist

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AddToWatchlist(

        @SerializedName("status_code")
        @Expose
        var statusCode: Int? = null,
        @SerializedName("status_message")
        @Expose
        var statusMessage: String? = null

)
