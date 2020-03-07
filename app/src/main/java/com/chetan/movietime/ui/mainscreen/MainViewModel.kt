package com.chetan.movietime.ui.mainscreen

import android.app.Application
import android.os.Bundle
import androidx.paging.PagedList
import com.chetan.movietime.adapters.FavouriteMoviesAdapter
import com.chetan.movietime.adapters.MovieAdapter
import com.chetan.movietime.adapters.MovieClickListener
import com.chetan.movietime.common.BaseViewModel
import com.chetan.movietime.data.Movie
import com.chetan.movietime.data.MoviesRepository
import com.chetan.movietime.ui.moviedetails.MovieDetailActivity
import com.rebel.demo.common.Navigator


/**
 * Created by Chetan on 2020-03-05.
 */
const val BUNDLE_MOVIE = "BUNDLE_MOVIE"

class MainViewModel(val app: Application, private val moviesRepository: MoviesRepository) :
    BaseViewModel(app) {

    fun getMovies() = moviesRepository.getMoviesPaginated()

    fun networkStatus() = moviesRepository.getNetworkStatus()

    fun isInitialRun() = moviesRepository.initialRunning()

    fun getFavouriteMovies() = moviesRepository.getFavouriteMovies()

    internal lateinit var adapter: MovieAdapter
    internal lateinit var favouriteMoviesAdapter: FavouriteMoviesAdapter


    private val movieClickListener = object : MovieClickListener {
        override fun onMovieClick(movie: Movie, position: Int) {

            val bundle = Bundle()
            bundle.putSerializable(BUNDLE_MOVIE, movie)

            navigationUtils.postValue(
                Navigator(
                    Navigator.NavigationAction.StartActivity,
                    MovieDetailActivity::class.java,
                    bundle
                )
            )
        }

        override fun onFavouriteClick(movie: Movie, position: Int) {
            movie.favourite = !movie.favourite
            moviesRepository.addOrUpdate(movie)
            //TODO : Save or remove movie from the local storage
        }
    }

    init {
        setAdapter()
    }

    private fun setAdapter() {
        adapter = MovieAdapter(listener = movieClickListener)

        favouriteMoviesAdapter =
            FavouriteMoviesAdapter(listener = movieClickListener)
    }

    fun submitList(list: PagedList<Movie>?) {
        adapter.submitList(list)
    }

    fun submitFavouriteMovies(list: List<Movie>) {
        favouriteMoviesAdapter.submitList(list)
    }
}