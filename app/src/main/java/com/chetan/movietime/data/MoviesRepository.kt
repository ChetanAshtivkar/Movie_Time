package com.chetan.movietime.data

import android.util.Log
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.chetan.movietime.data.network.RetrofitFactory
import com.chetan.movietime.data.pagination.MovieDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Created by Chetan on 2020-03-05.
 */
class MoviesRepository {

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
        LivePagedListBuilder<Int, Movie>(MovieDataSourceFactory(), pagedListConfig).build()

    fun getNetworkStatus() = Transformations.switchMap(
        MovieDataSourceFactory().getMutableData()
    ) { dataSource -> dataSource.getNetworkState() }

    fun initialRunning() = Transformations.switchMap(
        MovieDataSourceFactory().getMutableData()
    ) { dataSource -> dataSource.isInitialLoading() }
}