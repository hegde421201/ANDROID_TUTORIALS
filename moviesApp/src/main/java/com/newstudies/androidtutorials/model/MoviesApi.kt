package com.newstudies.androidtutorials.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface MoviesApi {

    @GET("movie")
    fun getMovies(@QueryMap params : Map<String, String>) : Single<MovieModel>
}