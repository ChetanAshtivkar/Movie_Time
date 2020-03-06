package com.chetan.movietime.adapters

import com.chetan.movietime.data.Movie

interface MovieClickListener {
    fun onMovieClick(movie: Movie, position: Int)

    fun onFavouriteClick(movie: Movie, position: Int)
}