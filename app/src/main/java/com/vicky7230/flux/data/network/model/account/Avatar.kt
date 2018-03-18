package com.vicky7230.flux.data.network.model.account

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Avatar(
    @SerializedName("gravatar")
    @Expose
    var gravatar: Gravatar? = null
)