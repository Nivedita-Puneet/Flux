package com.vicky7230.flux.data.network.model.tvDetails

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by vicky on 25/3/18.
 */

@Parcelize
data class ReviewResult(

        @SerializedName("author")
        @Expose
        var author: String? = null,
        @SerializedName("content")
        @Expose
        var content: String? = null,
        @SerializedName("id")
        @Expose
        var id: String? = null,
        @SerializedName("url")
        @Expose
        var url: String? = null

) : Parcelable
