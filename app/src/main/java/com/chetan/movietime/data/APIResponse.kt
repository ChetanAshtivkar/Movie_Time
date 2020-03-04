package com.chetan.movietime.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Chetan on 2020-03-05.
 */
data class APIResponse(

    val page: Int,

    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    val results: List<Movie>

)