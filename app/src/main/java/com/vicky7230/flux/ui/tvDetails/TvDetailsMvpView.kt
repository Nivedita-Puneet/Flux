package com.vicky7230.flux.ui.tvDetails

import com.vicky7230.flux.data.network.model.tvDetails.Videos
import com.vicky7230.flux.data.network.model.tvDetails.TvDetails
import com.vicky7230.flux.ui.base.MvpView

/**
 * Created by vicky on 21/3/18.
 */
interface TvDetailsMvpView : MvpView {
    fun showDetails(tvDetails: TvDetails?)
    fun showLoginScreen()

}