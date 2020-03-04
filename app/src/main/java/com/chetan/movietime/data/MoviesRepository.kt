package com.chetan.movietime.data

import android.util.Log
import com.chetan.movietime.data.network.RetrofitFactory
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
}