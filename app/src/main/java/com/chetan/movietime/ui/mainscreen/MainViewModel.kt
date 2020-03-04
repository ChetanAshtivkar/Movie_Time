package com.chetan.movietime.ui.mainscreen

import android.app.Application
import com.chetan.movietime.common.BaseViewModel
import com.chetan.movietime.data.MoviesRepository

/**
 * Created by Chetan on 2020-03-05.
 */

class MainViewModel(val app: Application, private val repo: MoviesRepository = MoviesRepository()) :
    BaseViewModel(app) {

}