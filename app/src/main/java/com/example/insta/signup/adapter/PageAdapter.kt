package com.example.insta.signup.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.insta.signup.email.SignupEmailFragment
import com.example.insta.signup.phone.SignupPhoneFragment

class PageAdapter(fm:FragmentManager):FragmentStatePagerAdapter(fm) {

    val data1 : Fragment =SignupPhoneFragment()
    val data2 : Fragment =SignupEmailFragment()

    val mFragmentData : ArrayList<Fragment> = arrayListOf(data1,data2)
    val mTitleData : ArrayList<String> = arrayListOf("전화번호","이메일")

    override fun getCount():Int = mFragmentData.size

    override fun getItem(position: Int): Fragment =mFragmentData[position]

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTitleData[position]
    }



}