package com.vicky7230.flux.ui.home.profile

import com.vicky7230.flux.data.network.model.account.Account
import com.vicky7230.flux.ui.base.MvpView

/**
 * Created by vicky on 18/3/18.
 */
interface ProfileMvpView : MvpView{
    fun showAccountDetails(account: Account)
}