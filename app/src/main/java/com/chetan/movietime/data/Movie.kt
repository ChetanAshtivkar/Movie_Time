package com.chetan.movietime.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Chetan on 2020-03-05.
 */
data class Movie(

    val popularity: Double,

    @SerializedName(value = "vote_count")
    val voteCount: Int,

    val video: Boolean,

    @SerializedName(value = "poster_path")
    val posterPath: String,

    val id: Int,

    val adult: Boolean,

    @SerializedName(value = "backdrop_path")
    val backdropPath: String,

    @SerializedName(value = "original_language")
    val originalLanguage: String,

    @SerializedName(value = "original_title")
    val originalTitle: String,

    @SerializedName(value = "genre_ids")
    val genreIds: List<Int>,

    val title: String,

    @SerializedName(value = "vote_average")
    val voteAverage: Double,

    val overview: String,

    @SerializedName(value = "release_date")
    val releaseDate: String
)