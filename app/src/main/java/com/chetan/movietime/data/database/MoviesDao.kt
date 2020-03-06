package com.chetan.movietime.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chetan.movietime.data.Movie

/**
 * Created by Chetan on 2020-03-05.
 */

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movie")
    fun getAll(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE favourite")
    fun getAllFavourite(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(movie: Movie): Long

    @Update
    fun update(movie: Movie)

    @Query("SELECT COUNT(*) FROM movie")
    fun getCount(): Int

    @Transaction
    fun addOrUpdate(movie: Movie) {
        val id = insertIgnore(movie).toInt()
        if (id == -1) {
            update(movie)
        }
    }
}