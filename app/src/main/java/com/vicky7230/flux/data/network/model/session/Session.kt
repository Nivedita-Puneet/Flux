package com.vicky7230.flux.data.network.model.session

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by vicky on 16/3/18.
 */
data class Session(
    @SerializedName("success")
    @Expose
    var success: Boolean? = null,
    @SerializedName("session_id")
    @Expose
    var sessionId: String? = null
)