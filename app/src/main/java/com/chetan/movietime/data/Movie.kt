package com.chetan.movietime.data

import androidx.databinding.BaseObservable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Chetan on 2020-03-05.
 */
@Entity(tableName = "movie")
@TypeConverters(CommonTypeConverters::class)
data class Movie(

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val popularity: Double,

    @SerializedName("vote_count")
    val voteCount: Int,

    val video: Boolean,

    @SerializedName("poster_path")
    val posterPath: String?,

    val adult: Boolean,

    var favourite: Boolean = false,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("genre_ids")
    val genreIds: List<Int>?,

    val title: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    val overview: String,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("belongs_to_collection")
    val belongsToCollection: String?,

    val budget: Int?,

    val genres: List<Genres>?,

    val homepage: String?,

    @SerializedName("imdb_id")
    val imdbId: String?,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanies>?,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountries>?,

    val revenue: Int?,

    val runtime: Int?,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguages>?,

    val status: String?,

    @SerializedName("tagline")
    val tagLine: String?

) : BaseObservable(), Serializable


data class Genres(
    val id: Int?,
    val name: String?
) : BaseObservable(), Serializable

data class SpokenLanguages(

    val iso_639_1: String?,

    val name: String?
) : BaseObservable(), Serializable

data class ProductionCountries(

    val iso_3166_1: String?,

    val name: String?
) : BaseObservable(), Serializable

data class ProductionCompanies(

    val id: Int?,

    @SerializedName("logo_path")
    val logoPath: String?,

    val name: String?,

    @SerializedName("origin_country")
    val originCountry: String?
) : BaseObservable(), Serializable