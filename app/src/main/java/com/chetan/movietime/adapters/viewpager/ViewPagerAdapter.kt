package com.chetan.movietime.adapters.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by Chetan on 2020-03-05.
 */

class ViewPagerAdapter(manager: FragmentManager) :
    FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList = ArrayList<Fragment>()
    private val pageTitleList = ArrayList<String>()

    fun addFragment(fragment: Fragment, pageTitle: String) {
        fragmentList.add(fragment)
        pageTitleList.add(pageTitle)
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pageTitleList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}