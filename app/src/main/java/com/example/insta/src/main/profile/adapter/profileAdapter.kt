package com.example.insta.src.main.profile.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

import com.example.insta.src.main.profile.ProfilefeedFragment
import com.example.insta.src.main.profile.TagFragment

class profileAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    val data1 : Fragment = ProfilefeedFragment()
    val data2 : Fragment = TagFragment()

    val mFragmentData : ArrayList<Fragment> = arrayListOf(data1,data2)

    override fun getCount(): Int=mFragmentData.size

    override fun getItem(position: Int): Fragment=mFragmentData[position]

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }
    override fun getPageTitle(position: Int): CharSequence? {
        //return null to display only the icon
        return null
    }
}