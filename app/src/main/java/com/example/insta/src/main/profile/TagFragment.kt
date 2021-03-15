package com.example.insta.src.main.profile

import android.os.Bundle
import android.view.View
import com.example.insta.R
import com.example.insta.config.BaseFragment
import com.example.insta.databinding.FragmentTagBinding

class TagFragment:BaseFragment<FragmentTagBinding>(FragmentTagBinding::bind, R.layout.fragment_tag) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}