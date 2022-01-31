package com.newstudies.androidtutorials.model

import android.app.Application
import com.newstudies.androidtutorials.utils.getKey
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApiService {

    private val BASE_URL = "https://api.themoviedb.org/3/search/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(MoviesApi::class.java)

    fun getMoviesBySearch(application: Application):Single<MovieModel>{
        val params: MutableMap<String, String> = HashMap()
        params["api_key"] = getKey(application)
        params["query"] = "james+bond"

        return api.getMovies(params)
    }
}