package com.example.insta.signup.Final

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.insta.login.LoginActivity
import com.example.insta.R
import com.example.insta.config.BaseActivity
import com.example.insta.databinding.ActivityFinalBinding
import com.example.insta.signup.models.PostSignUpRequest
import com.example.insta.signup.models.SignUpResponse
import java.text.SimpleDateFormat

class FinalActivity : BaseActivity<ActivityFinalBinding>(ActivityFinalBinding::inflate),
    FinalActivityView {

    private var userName: String? = null
    private var userEmail: String? = null
    private var userPassword: String? = null
    private var Birth: String? = null
    private var userNickName: String? = null
    private var userDiscloserScope: String? = null
    private var userBirth: java.util.Date? = null
    private var userProfile: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra("email") && intent.hasExtra("name") && intent.hasExtra("pwd")
            && intent.hasExtra("birth") && intent.hasExtra("nick") && intent.hasExtra("scope") && intent.hasExtra(
                "userProfilePicture"
            )
        ) {
            userProfile = intent.getParcelableExtra("userProfilePicture")
            Birth = intent.getStringExtra("birth")
            userName = intent.getStringExtra("name")
            userEmail = intent.getStringExtra("email")
            userPassword = intent.getStringExtra("pwd")
            userNickName = intent.getStringExtra("nick")
            userDiscloserScope = intent.getStringExtra("scope")
            val format = SimpleDateFormat("yyyy-MM-dd")
            userBirth = format.parse(Birth!!)
        }
        Log.d("확인", "최종 확인:" + userProfile)
        if (userProfile != null) {
            //val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, userProfile)
            //binding.profileImg.setImageBitmap(bitmap)
            binding.profileImg.scaleType= ImageView.ScaleType.CENTER_CROP
            Glide.with(this).load(userProfile).apply(RequestOptions().circleCrop()).into(binding.profileImg)
            Log.d("확인", "${Birth}+${userName}+${userEmail}+${userPassword}+${userNickName}+${userDiscloserScope}"
            )
        }
        else {
            binding.profileImg.setImageResource(R.drawable.no_img)
        }

        binding.finalBtn.setOnClickListener {

            val postRequest = PostSignUpRequest(
                userEmail = userEmail!!,
                userName = userName!!,
                userPassword = userPassword!!,
                userBirth = userBirth!!,
                userNickName = userNickName!!,
                userDisclosureScope = userDiscloserScope!!.toInt(),
                userProfilePicture = userProfile.toString(),
                profileCreateDate = userBirth!!
            )
            showLoadingDialog(this)
            FinalService(this).tryPostSignUp(postRequest)
        }


    }


    override fun onPostSignUpSuccess(response: SignUpResponse) {
        dismissLoadingDialog()
        response.message?.let {
            Log.d("확인", "가입 성공" + it)
        }
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onPostSignUpFailure(message: String) {
        dismissLoadingDialog()
        Log.d("확인", message)
    }

}