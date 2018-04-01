package com.vicky7230.flux.data.network.model.setFavourite

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SetFavourite(

        @SerializedName("status_code")
        @Expose
        var statusCode: Int? = null,
        @SerializedName("status_message")
        @Expose
        var statusMessage: String? = null

)
