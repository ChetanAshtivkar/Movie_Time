package com.chetan.movietime.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.chetan.movietime.data.Movie

/**
 * Created by Chetan on 2020-03-05.
 */

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movie")
    fun getAll(): LiveData<List<Movie>>


}