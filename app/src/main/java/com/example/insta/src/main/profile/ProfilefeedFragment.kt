package com.example.insta.src.main.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.insta.R
import com.example.insta.config.ApplicationClass
import com.example.insta.config.BaseFragment
import com.example.insta.databinding.FragmentProfilefeedBinding
import com.example.insta.src.main.profile.adapter.ProfileFeedAdapter
import com.example.insta.src.main.profile.models.ProfileResponse

class ProfilefeedFragment:BaseFragment<FragmentProfilefeedBinding>(FragmentProfilefeedBinding::bind,
    R.layout.fragment_profilefeed),ProfileFeedView {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.feedGridview.layoutManager=
            GridLayoutManager(requireContext(), 3)
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onGetProfileSuccess(response: ProfileResponse) {
        response.message.let {

            Log.d("확인",it.toString())
            dismissLoadingDialog()
        }
                //binding.feedGridview.adapter= ProfileFeedAdapter(requireContext(),response.data)


    }

    override fun onGetProfileFailure(message: String) {
        Log.d("확인",message)
        dismissLoadingDialog()
    }
}