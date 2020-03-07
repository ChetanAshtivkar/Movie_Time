package com.chetan.movietime.ui.moviedetails

import android.app.Application
import android.os.Bundle
import com.chetan.movietime.common.BaseViewModel
import com.chetan.movietime.data.MoviesRepository

/**
 * Created by Chetan on 2020-03-07.
 */

class MovieDetailViewModel(
    val app: Application,
    private val moviesRepository: MoviesRepository,
    extra: Bundle?
) :
    BaseViewModel(app) {

}