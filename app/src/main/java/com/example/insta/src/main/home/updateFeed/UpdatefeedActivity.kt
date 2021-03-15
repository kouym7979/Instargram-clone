package com.example.insta.src.main.home.updateFeed

import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.insta.config.BaseActivity
import com.example.insta.databinding.ActivityUpdatefeedBinding
import com.example.insta.src.main.home.models.FeedUpdateResponse

class UpdatefeedActivity :BaseActivity<ActivityUpdatefeedBinding>(ActivityUpdatefeedBinding::inflate),UpdateFeedView{

    private var feedIdx : Int?=null
    private var userProfile : String?=null
    private var caption : String?=null
    private var userNick:String?=null
    private var mediaUrl:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(intent.hasExtra("feedIdx") && intent.hasExtra("userProfile")&& intent.hasExtra("userNick")
            &&intent.hasExtra("mediaUrl") && intent.hasExtra("caption")){
            feedIdx=intent.getIntExtra("feedIdx",0)
            userNick=intent.getStringExtra("userNick")
            userProfile=intent.getStringExtra("userProfile")
            caption=intent.getStringExtra("caption")
            mediaUrl=intent.getStringExtra("mediaUrl")
        }

        Log.d("확인","인덱스:"+feedIdx.toString()+" 프로필:"+userProfile+" 닉네임:"+userNick+" caption:"+caption+" mediaUrl:"+mediaUrl)

        binding.backBtn.setOnClickListener { finish() }

        Glide.with(this).load(Uri.parse(userProfile)).apply(RequestOptions().circleCrop()).into(binding.updateUserImg)
        Glide.with(this).load(Uri.parse(mediaUrl)).into(binding.updateImg)
        binding.updateText.setText(caption)
        binding.updateUserNick.text=userNick


        binding.nextBtn.setOnClickListener {

            showLoadingDialog(this)
            UpdateFeedService(this).tryUpdateFeed(feedIdx!!,binding.updateText.text.toString())
        }



    }


    override fun onUpdateFeedSuccess(response: FeedUpdateResponse) {
       response.message.let {
           Log.d("확인",it)
           dismissLoadingDialog()
       }
        finish()
    }

    override fun onUpdateFeedFailure(message: String) {
        Log.d("확인",message)
        dismissLoadingDialog()
    }


}