package com.vicky7230.flux.data.network

import com.vicky7230.flux.data.network.model.authentication.AuthenticationToken
import com.vicky7230.flux.data.network.model.configuration.Configuration
import com.vicky7230.flux.data.network.model.genres.Genres
import com.vicky7230.flux.data.network.model.account.Account
import com.vicky7230.flux.data.network.model.results.Results
import com.vicky7230.flux.data.network.model.session.Session
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by vicky on 31/12/17.
 */
class AppApiHelper @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override fun getConfigurations(apiKey: String): Observable<Configuration> {
        return apiService.getConfigurations(apiKey)
    }

    override fun getGenresTv(apiKey: String): Observable<Genres> {
        return apiService.getGenresTv(apiKey)
    }

    override fun getGenresMovies(apiKey: String): Observable<Genres> {
        return apiService.getGenresMovies(apiKey)
    }

    override fun getTvByGenres(
        apiKey: String,
        withGenres: String,
        page: String,
        sortBy: String,
        voteAverage: String
    ): Observable<Results> {
        return apiService.getTvByGenres(apiKey, withGenres, page, sortBy, voteAverage)
    }

    override fun requestAuthenticationToken(apiKey: String): Observable<AuthenticationToken> {
        return apiService.requestAuthenticationToken(apiKey)
    }

    override fun getSessionId(apiKey: String, requestToken: String): Observable<Session> {
        return apiService.getSessionId(apiKey, requestToken)
    }

    override fun getAccountDetails(apiKey: String, sessionId: String): Observable<Account> {
        return apiService.getAccountDetails(apiKey, sessionId)
    }
}
