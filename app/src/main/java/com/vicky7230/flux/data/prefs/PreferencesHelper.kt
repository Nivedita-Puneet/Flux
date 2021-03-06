package com.vicky7230.flux.data.prefs

import io.reactivex.Observable

/**
 * Created by vicky on 1/3/18.
 */
interface PreferencesHelper {
    fun setUserGenres(genres: String?): Observable<Boolean>
    fun getUserGenres(): String?
    fun setBaseImageUrl(url: String?)
    fun getBaseImageUrl(): String?
    fun setGenresSelected()
    fun getGenresSelected(): Boolean
    fun setIsUserLoggedIn()
    fun getIsUserLoggedIn(): Boolean
    fun setSessionIdIntoPreference(sessionId: String?)
    fun getSessionIdFromPreference(): String?
    fun setAccountId(accountId: Int?)
    fun getAccountId(): Int?
    fun getUserName(): String?
    fun setUserName(userName: String?)
    fun getName(): String?
    fun setName(name: String?)
}