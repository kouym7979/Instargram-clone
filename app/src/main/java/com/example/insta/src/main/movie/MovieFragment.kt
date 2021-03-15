package com.example.insta.src.main.movie

import android.os.Bundle
import android.view.View
import com.example.insta.R
import com.example.insta.config.BaseFragment
import com.example.insta.databinding.FragmentMovieBinding

class MovieFragment:BaseFragment<FragmentMovieBinding>(FragmentMovieBinding::bind, R.layout.fragment_movie) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}