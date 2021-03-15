package com.example.insta.src.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.insta.R
import com.example.insta.config.ApplicationClass
import com.example.insta.config.BaseFragment
import com.example.insta.databinding.FragmentHomeBinding
import com.example.insta.src.main.feed.AddFeedActivity
import com.example.insta.src.main.home.adapter.FeedRecycler
import com.example.insta.src.main.home.adapter.StoryAdapter
import com.example.insta.src.main.home.media.MediaView
import com.example.insta.src.main.home.models.HomeFeedResponse
import com.example.insta.src.main.home.models.HomeMediaResponse
import com.example.insta.src.main.home.models.StoryResponse

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home)
    ,HomeFeedView,MediaView,FeedRecycler.EventHandler {

    var count :Int=0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.storyRecycler.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.plusBtn.setOnClickListener {
            startActivity(Intent(requireContext(),AddFeedActivity::class.java))
        }
        binding.feed.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        //binding.feed.requestFocus()
        var profileUrl=ApplicationClass.sSharedPreferences.getString("userProfilePicture", "")
        if(profileUrl!!.contains("alt=")){
            profileUrl=profileUrl.split("alt=").first()
            profileUrl=profileUrl.substring(0,profileUrl.length-1)
            profileUrl+="?alt=media"
            Log.d("Url",profileUrl.toString())
        }


        binding.userStoryImg.scaleType = ImageView.ScaleType.CENTER_CROP



        Glide.with(this).load(profileUrl).apply(
            RequestOptions().circleCrop()
        ).into(binding.userStoryImg)


    }

    override fun onStart() {
        super.onStart()
        showLoadingDialog(requireContext())
        HomeFeedService(this).tryGetFeed()



        HomeFeedService(this).tryGetStory(ApplicationClass.sSharedPreferences.getInt("userNickNameIdx",0))


    }

    override fun onGetFeedSuccess(response: HomeFeedResponse) {
        response.message.let{
            Log.d("확인",it.toString())
        }

        Log.d("확인",response.message.toString())

        binding.feed.adapter= FeedRecycler(requireContext(),response.data.asReversed(),this)

        dismissLoadingDialog()
    }

    override fun onGetFeedFailure(message: String) {
        dismissLoadingDialog()
        Log.d("확인",message)
    }

    override fun onGetStorySuccess(response: StoryResponse) {



        binding.storyRecycler.adapter=StoryAdapter(requireContext(),response.data)

    }

    override fun onGetStoryFailure(message: String) {

        Log.d("확인",message)
    }

    override fun onGetMediaSuccess(response: HomeMediaResponse) {
        response.message.let{
            Log.d("확인",it.toString())

        }
        dismissLoadingDialog()
    }

    override fun onGetMediaFailure(message: String) {
        dismissLoadingDialog()
        Log.d("확인",message)
    }

    override fun handle(position: Int, check: Boolean) {
       Log.d("확인","테스트 중입니다")
        if(check==true){
            showLoadingDialog(requireContext())
            HomeFeedService(this).tryGetFeed()
        }

        binding.commentEdit.isFocusableInTouchMode=true

        var profileUrl=ApplicationClass.sSharedPreferences.getString("userProfilePicture","")
        if(profileUrl!!.contains("alt=")){
            profileUrl=profileUrl.split("alt=").first()
            profileUrl=profileUrl.substring(0,profileUrl.length-1)
            profileUrl+="?alt=media"
            Log.d("Url",profileUrl.toString())
        }

        Glide.with(this).load(profileUrl).apply(
            RequestOptions().circleCrop()
        ).into(binding.commentEditImg)

        binding.uploadBtn.visibility=View.VISIBLE
        binding.commentEdit.visibility=View.VISIBLE
        binding.commentEditImg.visibility=View.VISIBLE
        binding.view7.visibility=View.VISIBLE
        binding.view5.visibility=View.VISIBLE
        binding.emoticon.visibility=View.VISIBLE
        binding.commentEdit.requestFocus()

    }


}