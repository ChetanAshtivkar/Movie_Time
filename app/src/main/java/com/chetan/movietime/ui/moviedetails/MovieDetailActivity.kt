package com.chetan.movietime.ui.moviedetails

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.chetan.movietime.R
import com.chetan.movietime.common.BaseActivity
import com.chetan.movietime.data.Movie
import com.chetan.movietime.data.MoviesRepository
import com.chetan.movietime.data.database.MoviesDatabase
import com.chetan.movietime.databinding.ActivityMovieDetailBinding
import com.chetan.movietime.ui.mainscreen.BUNDLE_MOVIE

class MovieDetailActivity : BaseActivity() {
    override fun setupLiveDataComponents() {


    }

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var viewModel: MovieDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail)

        val movieDao = MoviesDatabase.getInstance(application)!!.movieDao()

        viewModel = ViewModelProvider(
            this,
            MovieDetailViewModelFactory(
                application, MoviesRepository.getInstance(movieDao),
                intent.extras
            )
        ).get(MovieDetailViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        intent.extras?.let {
            binding.movie = it.getSerializable(BUNDLE_MOVIE) as Movie?
        }
    }
}
