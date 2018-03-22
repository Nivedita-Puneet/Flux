package com.vicky7230.flux.ui.tvDetails

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.vicky7230.flux.ui.tvDetails.info.InfoFragment

/**
 * Created by vicky on 22/3/18.
 */
class ViewsPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return InfoFragment.newInstance()
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Info"
    }
}