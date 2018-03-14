package com.vicky7230.flux.data.network

import com.vicky7230.flux.data.network.model.configuration.Configuration
import com.vicky7230.flux.data.network.model.genres.Genres
import com.vicky7230.flux.data.network.model.results.Results
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by vicky on 31/12/17.
 */
interface ApiService {

    @GET("genre/tv/list")
    fun getGenres(
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
}