package com.chetan.movietime.data

import androidx.databinding.BaseObservable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Chetan on 2020-03-05.
 */
@Entity(tableName = "movie")
data class Movie(

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val popularity: Double,

    @SerializedName(value = "vote_count")
    val voteCount: Int,

    val video: Boolean,

    @SerializedName(value = "poster_path")
    val posterPath: String?,

    val adult: Boolean,

    @SerializedName(value = "backdrop_path")
    val backdropPath: String?,

    @SerializedName(value = "original_language")
    val originalLanguage: String,

    @SerializedName(value = "original_title")
    val originalTitle: String,

//    @SerializedName(value = "genre_ids")
//    @TypeConverters(MyTypeConverter::class)
//    val genreIds: List<Int>,

    val title: String,

    @SerializedName(value = "vote_average")
    val voteAverage: Double,

    val overview: String,

    @SerializedName(value = "release_date")
    val releaseDate: String
) : BaseObservable(), Serializable