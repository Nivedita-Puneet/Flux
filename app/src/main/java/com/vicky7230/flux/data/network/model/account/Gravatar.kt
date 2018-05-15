package com.vicky7230.flux.data.network.model.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Gravatar(

    @SerializedName("hash")
    @Expose
    var hash: String? = null

)
