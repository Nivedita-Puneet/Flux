package com.vicky7230.flux.data.network.model.tvDetails

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cast(

        @SerializedName("character")
        @Expose
        var character: String? = null,
        @SerializedName("credit_id")
        @Expose
        var creditId: String? = null,
        @SerializedName("gender")
        @Expose
        var gender: Int? = null,
        @SerializedName("id")
        @Expose
        var id: Int? = null,
        @SerializedName("name")
        @Expose
        var name: String? = null,
        @SerializedName("order")
        @Expose
        var order: Int? = null,
        @SerializedName("profile_path")
        @Expose
        var profilePath: String? = null

) : Parcelable