package com.vicky7230.flux.ui.tvDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Watchlist(

        @SerializedName("media_type")
        @Expose
        var mediaType: String? = "tv",
        @SerializedName("media_id")
        @Expose
        var mediaId: Int? = null,
        @SerializedName("watchlist")
        @Expose
        var watchlist: Boolean? = true

)
