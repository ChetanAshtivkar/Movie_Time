package com.chetan.movietime.ui.mainscreen.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chetan.movietime.R
import com.chetan.movietime.data.MoviesRepository
import com.chetan.movietime.data.database.MoviesDatabase
import com.chetan.movietime.databinding.FragmentFavouriteMoviesBinding
import com.chetan.movietime.ui.mainscreen.MainViewModel
import com.chetan.movietime.ui.mainscreen.MainViewModelFactory

class FavouriteMoviesFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    companion object {
        fun getInstance(bundle: Bundle = Bundle()): FavouriteMoviesFragment {
            val fragment = FavouriteMoviesFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val movieDao = MoviesDatabase.getInstance(activity!!.application)!!.movieDao()

        val binding = DataBindingUtil.inflate<FragmentFavouriteMoviesBinding>(
            inflater, R.layout.fragment_favourite_movies, container,
            false
        )

        viewModel = activity?.run {
            ViewModelProvider(
                this,
                MainViewModelFactory(
                    activity!!.application,
                    MoviesRepository.getInstance(movieDao)
                )
            ).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        binding.movieList.adapter = viewModel.favouriteMoviesAdapter

        return binding.root
    }
}
