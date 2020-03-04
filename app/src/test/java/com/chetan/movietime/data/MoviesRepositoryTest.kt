package com.chetan.movietime.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith

/**
 * Created by Chetan on 2020-03-05.
 */

@RunWith(AndroidJUnit4::class)
class MoviesRepositoryTest {

    @Test
    fun test() {
        MoviesRepository().getMovies()
    }
}