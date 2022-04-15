package com.newstudies.androidtutorials.model

import android.icu.text.CaseMap
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.sql.Date
import java.util.*
import kotlin.collections.ArrayList

data class MoviePalette(var color:Int)



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
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readValue(List::class.java.classLoader) as? List<Int>,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
       return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(releaseDate)
        p0?.writeString(posterImage)
        p0?.writeArray(arrayOf(genreIDs))
    }

    companion object CREATOR : Parcelable.Creator<Results> {
        override fun createFromParcel(parcel: Parcel): Results {
            return Results(parcel)
        }

        override fun newArray(size: Int): Array<Results?> {
            return arrayOfNulls(size)
        }
    }
}