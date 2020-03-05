package com.chetan.movietime.ui.mainscreen

import android.app.Application
import androidx.paging.PagedList
import com.chetan.movietime.adapters.MovieAdapter
import com.chetan.movietime.adapters.MovieListener
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
        adapter = MovieAdapter(MovieListener { movie, position ->
            //TODO : Intent to new screen
        })
    }

    fun submitList(list: PagedList<Movie>?) {
        adapter.submitList(list)
    }
}