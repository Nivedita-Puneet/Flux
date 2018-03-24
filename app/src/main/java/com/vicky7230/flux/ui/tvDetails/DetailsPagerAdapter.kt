package com.vicky7230.flux.ui.tvDetails

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.vicky7230.flux.ui.tvDetails.cast.CastFragment
import com.vicky7230.flux.ui.tvDetails.info.InfoFragment
import com.vicky7230.flux.ui.tvDetails.seasons.SeasonsFragment

/**
 * Created by vicky on 22/3/18.
 */
class DetailsPagerAdapter(fragmentManager: FragmentManager, private val fragments: MutableList<Fragment>) : FragmentPagerAdapter(fragmentManager) {

    fun addItems(fragments: MutableList<Fragment>) {
        this.fragments.addAll(fragments)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Overview"
            1 -> "Seasons"
            2 -> "Cast"
            else -> " "
        }
    }
}