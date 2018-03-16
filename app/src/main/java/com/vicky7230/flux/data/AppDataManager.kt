package com.vicky7230.flux.data

import com.vicky7230.flux.data.db.AppDbHelper
import com.vicky7230.flux.data.db.room.model.ConfigurationDbModel
import com.vicky7230.flux.data.network.AppApiHelper
import com.vicky7230.flux.data.network.model.authentication.AuthenticationToken
import com.vicky7230.flux.data.network.model.configuration.Configuration
import com.vicky7230.flux.data.network.model.genres.Genres
import com.vicky7230.flux.data.network.model.results.Results
import com.vicky7230.flux.data.network.model.session.Session
import com.vicky7230.flux.data.prefs.AppPreferencesHelper
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by vicky on 31/12/17.
 */
class AppDataManager @Inject
constructor(
    private val appApiHelper: AppApiHelper,
    private val appPreferencesHelper: AppPreferencesHelper,
    private val appDbHelper: AppDbHelper
) : DataManager {

    override fun setIsUserLoggedIn() {
        appPreferencesHelper.setIsUserLoggedIn()
    }

    override fun getIsUserLoggedIn(): Boolean {
        return appPreferencesHelper.getIsUserLoggedIn()
    }

    override fun setSessionIdIntoPreference(sessionId: String?) {
        appPreferencesHelper.setSessionIdIntoPreference(sessionId)
    }

    override fun getSessionIdFromPreference(): String? {
        return appPreferencesHelper.getSessionIdFromPreference()
    }

    override fun setGenresSelected() {
        appPreferencesHelper.setGenresSelected()
    }

    override fun getGenresSelected(): Boolean {
        return appPreferencesHelper.getGenresSelected()
    }

    override fun setBaseImageUrl(url: String?) {
        appPreferencesHelper.setBaseImageUrl(url)
    }

    override fun getBaseImageUrl(): String? {
        return appPreferencesHelper.getBaseImageUrl()
    }

    override fun deleteConfigurations() {
        appDbHelper.deleteConfigurations()
    }

    override fun getConfigurations(apiKey: String): Observable<Configuration> {
        return appApiHelper.getConfigurations(apiKey)
    }

    override fun insertConfigurations(configurations: MutableList<ConfigurationDbModel>): List<Long> {
        return appDbHelper.insertConfigurations(configurations)
    }

    override fun getGenresTv(apiKey: String): Observable<Genres> {
        return appApiHelper.getGenresTv(apiKey)
    }

    override fun getGenresMovies(apiKey: String): Observable<Genres> {
        return appApiHelper.getGenresMovies(apiKey)
    }

    override fun getTvByGenres(
        apiKey: String,
        withGenres: String,
        page: String
    ): Observable<Results> {
        return appApiHelper.getTvByGenres(apiKey, withGenres, page)
    }

    override fun setUserGenres(genres: String?): Observable<Boolean> {
        return appPreferencesHelper.setUserGenres(genres)
    }

    override fun getUserGenres(): String? {
        return appPreferencesHelper.getUserGenres()
    }

    override fun requestAuthenticationToken(apiKey: String): Observable<AuthenticationToken> {
        return appApiHelper.requestAuthenticationToken(apiKey)
    }

    override fun getSessionId(apiKey: String, requestToken: String): Observable<Session> {
        return appApiHelper.getSessionId(apiKey, requestToken)
    }
}
