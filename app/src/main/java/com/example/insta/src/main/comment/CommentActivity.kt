package com.example.insta.src.main.comment

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.Layout
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.insta.config.BaseActivity
import com.example.insta.databinding.ActivityCommentBinding
import com.bumptech.glide.request.RequestOptions
import com.example.insta.R
import com.example.insta.config.ApplicationClass
import com.example.insta.src.main.comment.adapter.CommentRecycler
import com.example.insta.src.main.comment.models.CommentDeleteResponse
import com.example.insta.src.main.comment.models.CommentInqueryResponse
import com.example.insta.src.main.comment.models.CommentRequest
import com.example.insta.src.main.comment.models.CommentResponse
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make as make1

class CommentActivity : BaseActivity<ActivityCommentBinding>(ActivityCommentBinding::inflate),CommentRecycler.EventHandler,
    CommentView {

    private var post_nick: String? = null
    private var post_userimg: String? = null
    private var caption: String? = null
    private var feedidx: Int = 0
    var comment_text: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra("postnick")
            && intent.hasExtra("postuserimg")
            && intent.hasExtra("caption") && intent.hasExtra("feedidx")
        ) {
            post_nick = intent.getStringExtra("postnick")
            post_userimg = intent.getStringExtra("postuserimg")
            caption = intent.getStringExtra("caption")
            feedidx = intent.getIntExtra("feedidx", 0)
        }

        binding.commentUserNick.text = post_nick
        binding.commentUserText.text = caption
        binding.commentUser.scaleType = ImageView.ScaleType.CENTER_CROP
        binding.commentEditImg.scaleType=ImageView.ScaleType.CENTER_CROP

        var Url=post_userimg
        if(Url!!.contains("alt=")){
            Url=Url.split("alt=").first()
            Url=Url.substring(0,Url.length-1)
            Url+="?alt=media"
            Log.d("Url",Url.toString())
        }
        Glide.with(this).load(Url).apply(
            RequestOptions().circleCrop()
        ).into(binding.commentUser)

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

        binding.commentRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.commentEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isEmpty()) {
                    binding.uploadBtn.isEnabled = false
                } else if (s.isNotEmpty()) {
                    binding.uploadBtn.isEnabled = true
                    binding.uploadBtn.setTextColor(
                        ContextCompat.getColor(applicationContext, R.color.next)
                    )
                }
                comment_text = binding.commentEdit.text.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        Log.d("확인", "피드인덱스" + feedidx + " 댓글내용:" + binding.commentEdit.text.toString())
        binding.uploadBtn.setOnClickListener {
            comment_text = binding.commentEdit.text.toString()
            val commentrequest = CommentRequest(
                commentText = comment_text.toString(),
                userNickNameIdx = ApplicationClass.sSharedPreferences.getInt("userNickNameIdx", 0)
            )
            showLoadingDialog(this)
            CommentService(this).tryPostComment(feedidx, commentrequest)

        }

       binding.backBtn.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()
        Log.d("확인", "통신전에 feedidx입니다:" + feedidx)
        showLoadingDialog(this)
        CommentService(this).tryGetComment(feedidx)
    }

    override fun onPostCommentSuccess(response: CommentResponse) {
        binding.commentEdit.setText("")
        dismissLoadingDialog()
        response.message?.let {
            Log.d("확인", "댓글생성 성공:" + it)
        }


        showLoadingDialog(this)
        CommentService(this).tryGetComment(feedidx)
    }

    override fun onPostCommentFailure(message: String) {
        dismissLoadingDialog()
        Log.d("확인", message)
    }

    override fun onGetCommentSuccess(response: CommentInqueryResponse) {

        response.message?.let {
            Log.d("확인", "댓글조회:" + it)
        }

        binding.commentRecycler.adapter = CommentRecycler(this, response.data,this)

        dismissLoadingDialog()
    }

    override fun onGetCommentFailure(message: String) {
        dismissLoadingDialog()
        Log.d("확인", message)
    }

    override fun onDeleteCommentSuccess(response: CommentDeleteResponse) {
        dismissLoadingDialog()
        response.message?.let {
            Log.d("확인", "댓글삭제 성공:" + it)
        }

        showLoadingDialog(this)
        CommentService(this).tryGetComment(feedidx)
    }

    override fun onDeleteCommentFailure(message: String) {
        dismissLoadingDialog()
        Log.d("확인", message)
    }

    override fun handle(position: Int,check:Boolean) {
        //if(check==true) {


        binding.commentToolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.barcolor))

        binding.delBackBtn.visibility=View.VISIBLE
        binding.delBtn.visibility=View.VISIBLE
        binding.delComment.visibility=View.VISIBLE

        binding.backBtn.visibility=View.INVISIBLE
        binding.comment.visibility=View.INVISIBLE
        binding.nextBtn.visibility=View.INVISIBLE
        window.statusBarColor= Color.parseColor("#0086DD")

        binding.delBtn.setOnClickListener {view->

            binding.delBackBtn.visibility=View.INVISIBLE
            binding.delBtn.visibility=View.INVISIBLE
            binding.delComment.visibility=View.INVISIBLE

            binding.backBtn.visibility=View.VISIBLE
            binding.comment.visibility=View.VISIBLE
            binding.nextBtn.visibility=View.VISIBLE
            val snackbar=Snackbar.make(view,"댓글 1개가 삭제되었습니다.",Snackbar.LENGTH_SHORT)
                .setTextColor(Color.WHITE)

                snackbar.setAction("되돌리기",null).setActionTextColor(Color.WHITE)
            snackbar.show()
            window.statusBarColor=Color.parseColor("#FCFCFC")

            CommentService(this).tryDeleteComment(position)

        }

    }


}

