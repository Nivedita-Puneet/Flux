package com.vicky7230.flux.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Favourite(

        @SerializedName("media_type")
        @Expose
        var mediaType: String? = "tv",
        @SerializedName("media_id")
        @Expose
        var mediaId: Int? = null,
        @SerializedName("favorite")
        @Expose
        var favorite: Boolean? = true

)
