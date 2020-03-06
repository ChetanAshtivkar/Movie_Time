package com.chetan.movietime.data.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.chetan.movietime.data.Movie
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Chetan on 2020-03-07.
 */

@RunWith(AndroidJUnit4::class)
class MoviesDatabaseTest {

    lateinit var database: MoviesDatabase
    lateinit var dao: MoviesDao


    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, MoviesDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.movieDao()
    }

    @Test
    fun testDB() {
        val movie = Movie(
            id = 1,
            favourite = true,
            title = "Movie Title",
            adult = false,
            backdropPath = "/test.jpg",
            originalLanguage = "En",
            originalTitle = "Original Title",
            overview = "Test overview",
            popularity = 3.5,
            posterPath = "/test.jpg",
            releaseDate = "2012-04-25",
            video = false,
            voteAverage = 4.4,
            voteCount = 500
        )

        dao.insert(movie)

        Assert.assertNotEquals(0, dao.getCount())

    }

    @After
    fun closeDb() {
        database.close()
    }

}