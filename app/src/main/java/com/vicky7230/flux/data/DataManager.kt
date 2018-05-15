package com.vicky7230.flux.data

import com.vicky7230.flux.data.db.DbHelper
import com.vicky7230.flux.data.network.ApiHelper
import com.vicky7230.flux.data.prefs.PreferencesHelper


/**
 * Created by vicky on 31/12/17.
 */
interface DataManager : ApiHelper, PreferencesHelper, DbHelper {
}