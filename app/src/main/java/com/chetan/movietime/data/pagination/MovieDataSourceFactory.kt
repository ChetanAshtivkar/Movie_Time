package com.chetan.movietime.data.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.chetan.movietime.data.Movie


/**
 * Created by Chetan on 2020-03-05.
 */

object MovieDataSourceFactory : DataSource.Factory<Int, Movie>() {

    private var mutableLiveData = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val feedDataSource = MovieDataSource()
        mutableLiveData.postValue(feedDataSource)
        return feedDataSource
    }

    fun getMutableData() = mutableLiveData

}