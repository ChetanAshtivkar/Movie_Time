package com.chetan.movietime.ui.mainscreen

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chetan.movietime.R
import com.chetan.movietime.adapters.viewpager.ViewPagerAdapter
import com.chetan.movietime.common.BaseActivity
import com.chetan.movietime.databinding.ActivityMainBinding
import com.chetan.movietime.ui.mainscreen.fragments.FavouriteMoviesFragment
import com.chetan.movietime.ui.mainscreen.fragments.TopMoviesFragment
import com.rebel.demo.common.Navigator

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun setupLiveDataComponents() {
        viewModel.navigationUtils.observe(this, Observer<Navigator> { this.resolveNavigation(it) })

        viewModel.isInitialRun().observe(this, Observer {

        })

        viewModel.getMovies().observe(this, Observer {
            viewModel.submitList(it)

        })

        viewModel.networkStatus().observe(this, Observer {

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(application)
        ).get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setViewPager()
    }


    private fun setViewPager() {
        binding.tabs.setupWithViewPager(binding.viewPager)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(TopMoviesFragment.getInstance(), getString(R.string.title_top_movies))
        adapter.addFragment(
            FavouriteMoviesFragment.getInstance(), getString(R.string.title_favourites_moview)
        )
        binding.viewPager.adapter = adapter
    }
}
