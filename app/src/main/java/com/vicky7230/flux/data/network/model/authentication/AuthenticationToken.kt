package com.vicky7230.flux.data.network.model.authentication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by vicky on 16/3/18.
 */

data class AuthenticationToken(
    @SerializedName("success")
    @Expose
    var success: Boolean? = null,
    @SerializedName("expires_at")
    @Expose
    var expiresAt: String? = null,
    @SerializedName("request_token")
    @Expose
    var requestToken: String? = null
)
