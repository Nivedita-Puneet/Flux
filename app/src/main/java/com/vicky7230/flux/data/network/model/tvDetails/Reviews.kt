package com.vicky7230.flux.data.network.model.tvDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by vicky on 25/3/18.
 */
data class Reviews(

        @SerializedName("page")
        @Expose
        var page: Int? = null,
        @SerializedName("results")
        @Expose
        var reviewResults: List<ReviewResult>? = null,
        @SerializedName("total_pages")
        @Expose
        var totalPages: Int? = null,
        @SerializedName("total_results")
        @Expose
        var totalResults: Int? = null

)