package com.newstudies.androidtutorials.model

import android.icu.text.CaseMap
import com.google.gson.annotations.SerializedName
import java.sql.Date
import java.util.*

//
data class MovieModel(

    val page : Int?,
    val total_pages : Int?,
    val total_results : Int?,

    @SerializedName("results")
    val results: List<Results>
)

data class Results(

    @SerializedName("adult")
    val rating : Boolean?,

    @SerializedName("backdrop_path")
    val backdropPath : String?,

    @SerializedName("genre_ids")
    val genreIDs : List<Int>?,

    @SerializedName("id")
    val movieID : Int?,

    @SerializedName("original_language")
    val language:String?,

    @SerializedName("original_title")
    val title: String?,

    val overview : String?,
    val popularity : Float?,

    @SerializedName("poster_path")
    val posterImage : String?,

    @SerializedName("release_date")
    val releaseDate : String?,
)