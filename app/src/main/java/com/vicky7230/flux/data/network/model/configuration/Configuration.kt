package com.vicky7230.flux.data.network.model.configuration

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Configuration(

    @SerializedName("images")
    @Expose
    var images: Images? = null,
    @SerializedName("change_keys")
    @Expose
    var changeKeys: MutableList<String>? = null

)
