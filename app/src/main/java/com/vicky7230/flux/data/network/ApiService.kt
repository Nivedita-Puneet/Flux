package com.vicky7230.flux.data.network

import com.vicky7230.flux.data.network.model.authentication.AuthenticationToken
import com.vicky7230.flux.data.network.model.configuration.Configuration
import com.vicky7230.flux.data.network.model.genres.Genres
import com.vicky7230.flux.data.network.model.results.Results
import com.vicky7230.flux.data.network.model.session.Session
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by vicky on 31/12/17.
 */
interface ApiService {

    @GET("genre/tv/list")
    fun getGenresTv(
        @Query("api_key") apiKey: String
    ): Observable<Genres>

    @GET("genre/movie/list")
    fun getGenresMovies(
        @Query("api_key") apiKey: String
    ): Observable<Genres>

    @GET("configuration")
    fun getConfigurations(
        @Query("api_key") apiKey: String
    ): Observable<Configuration>

    @GET("discover/tv")
    fun getTvByGenres(
        @Query("api_key") apiKey: String,
        @Query("with_genres") withGenres: String,
        @Query("page") page: String
    ): Observable<Results>

    @GET("authentication/token/new")
    fun requestAuthenticationToken(
        @Query("api_key") apiKey: String
    ): Observable<AuthenticationToken>

    @GET("authentication/session/new")
    fun getSessionId(
        @Query("api_key") apiKey: String,
        @Query("request_token") requestToken: String
    ): Observable<Session>


}