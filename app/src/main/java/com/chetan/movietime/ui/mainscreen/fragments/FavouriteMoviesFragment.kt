package com.chetan.movietime.ui.mainscreen.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chetan.movietime.R
import com.chetan.movietime.databinding.FragmentFavouriewMoviesBinding
import com.chetan.movietime.ui.mainscreen.MainViewModel
import com.chetan.movietime.ui.mainscreen.MainViewModelFactory

class FavouriteMoviesFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    companion object {
        fun getInstance(bundle: Bundle = Bundle()): TopMoviesFragment {
            val fragment = TopMoviesFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentFavouriewMoviesBinding>(
            inflater, R.layout.fragment_favouriew_movies, container,
            false
        )

        viewModel = activity?.run {
            ViewModelProvider(
                this,
                MainViewModelFactory(
                    activity!!.application
                )
            ).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")


        return binding.root
    }
}
