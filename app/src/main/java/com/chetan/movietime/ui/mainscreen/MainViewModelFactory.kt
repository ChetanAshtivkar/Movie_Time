package com.chetan.movietime.ui.mainscreen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chetan.movietime.data.MoviesRepository

class MainViewModelFactory(private val app: Application, private val repository: MoviesRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return MainViewModel(app = app, moviesRepository = repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
