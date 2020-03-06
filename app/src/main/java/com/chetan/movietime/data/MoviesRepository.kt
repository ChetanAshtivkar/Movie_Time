package com.chetan.movietime.data

import android.util.Log
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.chetan.movietime.data.database.MoviesDao
import com.chetan.movietime.data.network.RetrofitFactory
import com.chetan.movietime.data.pagination.MovieDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Created by Chetan on 2020-03-05.
 */
class MoviesRepository(val moviesDao: MoviesDao) {

    companion object {
        private var INSTANCE: MoviesRepository? = null

        fun getInstance(moviesDao: MoviesDao): MoviesRepository {
            if (INSTANCE == null) {
                INSTANCE = MoviesRepository(moviesDao)
            }
            return INSTANCE as MoviesRepository
        }
    }

    fun getMovies() = CoroutineScope(Dispatchers.IO).launch {

        val data = RetrofitFactory.makeRetrofitService().getMovies(1)

        val response = data.execute()
        val responseBody = response.body()

        responseBody?.let {
            Log.d("Movies", it.toString())
        }
    }

    var pagedListConfig: PagedList.Config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(40)
        .setPageSize(20).build()

    fun getMoviesPaginated() =
        LivePagedListBuilder<Int, Movie>(MovieDataSourceFactory, pagedListConfig).build()

    fun getNetworkStatus() = Transformations.switchMap(
        MovieDataSourceFactory.getMutableData()
    ) { dataSource -> dataSource.getNetworkState() }

    fun initialRunning() = Transformations.switchMap(
        MovieDataSourceFactory.getMutableData()
    ) { dataSource -> dataSource.isInitialLoading() }

    fun addOrUpdate(movie: Movie) {
        CoroutineScope(Dispatchers.IO).launch {
            moviesDao.addOrUpdate(movie)
        }
    }

    fun getFavouriteMovies() = moviesDao.getAllFavourite()
}