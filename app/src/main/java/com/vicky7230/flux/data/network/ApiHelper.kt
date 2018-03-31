package com.vicky7230.flux.data.network

import com.vicky7230.flux.data.network.model.authentication.AuthenticationToken
import com.vicky7230.flux.data.network.model.configuration.Configuration
import com.vicky7230.flux.data.network.model.genres.Genres
import com.vicky7230.flux.data.network.model.account.Account
import com.vicky7230.flux.data.network.model.results.Results
import com.vicky7230.flux.data.network.model.session.Session
import com.vicky7230.flux.data.network.model.tvDetails.Videos
import com.vicky7230.flux.data.network.model.tvDetails.TvDetails
import io.reactivex.Observable

/**
 * Created by vicky on 31/12/17.
 */
interface ApiHelper {

    fun getConfigurations(apiKey: String): Observable<Configuration>

    fun getGenresTv(apiKey: String): Observable<Genres>

    fun getGenresMovies(apiKey: String): Observable<Genres>

    fun getTvByGenres(
            apiKey: String,
            withGenres: String,
            page: String,
            sortBy: String,
            voteAverage: String
    ): Observable<Results>

    fun requestAuthenticationToken(apiKey: String): Observable<AuthenticationToken>

    fun getSessionId(apiKey: String, requestToken: String): Observable<Session>

    fun getAccountDetails(apiKey: String, sessionId: String): Observable<Account>

    fun getTvDetails(tvId: String, apiKey: String): Observable<TvDetails>

    fun getSearchResults(apiKey: String, query: String, page: String): Observable<Results>
}