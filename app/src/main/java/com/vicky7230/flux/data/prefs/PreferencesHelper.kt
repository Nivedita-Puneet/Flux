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
}