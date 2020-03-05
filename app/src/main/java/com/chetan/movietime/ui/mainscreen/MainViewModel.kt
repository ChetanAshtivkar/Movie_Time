package com.chetan.movietime.ui.mainscreen

import android.app.Application
import androidx.paging.PagedList
import com.chetan.movietime.adapters.MovieAdapter
import com.chetan.movietime.adapters.MovieClickListener
import com.chetan.movietime.common.BaseViewModel
import com.chetan.movietime.data.Movie
import com.chetan.movietime.data.MoviesRepository


/**
 * Created by Chetan on 2020-03-05.
 */

class MainViewModel(val app: Application) :
    BaseViewModel(app) {

    fun getMovies() = MoviesRepository().getMoviesPaginated()

    fun networkStatus() = MoviesRepository().getNetworkStatus()

    fun isInitialRun() = MoviesRepository().initialRunning()

    internal lateinit var adapter: MovieAdapter

    init {
        setAdapter()
    }

    private fun setAdapter() {
        adapter = MovieAdapter(object : MovieClickListener {
            override fun onMovieClick(movie: Movie, position: Int) {

            }

            override fun onFavouriteClick(movie: Movie, position: Int) {
                movie.favourite = !movie.favourite
                //TODO : Save or remove movie from the local storage
            }

        })
    }

    fun submitList(list: PagedList<Movie>?) {
        adapter.submitList(list)
    }
}