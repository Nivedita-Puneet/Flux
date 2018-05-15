package com.vicky7230.flux.data.network.model.configuration

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Images(

    @SerializedName("base_url")
    @Expose
    var baseUrl: String? = null,
    @SerializedName("secure_base_url")
    @Expose
    var secureBaseUrl: String? = null,
    @SerializedName("backdrop_sizes")
    @Expose
    var backdropSizes: MutableList<String>? = null,
    @SerializedName("logo_sizes")
    @Expose
    var logoSizes: MutableList<String>? = null,
    @SerializedName("poster_sizes")
    @Expose
    var posterSizes: MutableList<String>? = null,
    @SerializedName("profile_sizes")
    @Expose
    var profileSizes: MutableList<String>? = null,
    @SerializedName("still_sizes")
    @Expose
    var stillSizes: MutableList<String>? = null

)
