package com.example.insta.src.main.feed.caption

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.insta.config.ApplicationClass
import com.example.insta.config.BaseActivity
import com.example.insta.databinding.ActivityAddcaptionBinding
import com.example.insta.src.main.feed.AddFeedService
import com.example.insta.src.main.feed.AddFeedView
import com.example.insta.src.main.feed.models.FeedResponse
import com.example.insta.src.main.feed.models.MediaUrl
import com.example.insta.src.main.feed.models.PostFeedRequest
import com.example.insta.src.main.MainActivity
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class AddcaptionActivity : BaseActivity<ActivityAddcaptionBinding>(ActivityAddcaptionBinding::inflate),AddFeedView {
    var storage : FirebaseStorage?=null
    var imgArr =ArrayList<String>()
    var media = ArrayList<MediaUrl>()
    var final=ArrayList<Uri>()
    var date:LocalDateTime?=null
    var storage_uri=ArrayList<String>()
    var mDate:Date?=null
    var sub:String?=null
    private var isRunning=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storage= FirebaseStorage.getInstance()

        if(intent.hasExtra("img") && intent.hasExtra("sub")){
            imgArr= intent.getStringArrayListExtra("img") as ArrayList<String>
            sub=intent.getStringExtra("sub")
        }
        for(i in imgArr.indices)
            Log.d("전달","url:"+imgArr[i])

        binding.backBtn.setOnClickListener { finish() }

        binding.nextFeedImg.scaleType=ImageView.ScaleType.CENTER_CROP
        val bitmap = BitmapFactory.decodeFile(sub)
        binding.nextFeedImg.setImageBitmap(bitmap)//골랐던 사진들 중에 첫번째 사진을 표현

        date=LocalDateTime.now()
        val now: Long = System.currentTimeMillis()
        mDate = Date(now)


        val thread=ThreadClass()
        thread.start()
        binding.nextBtn.isEnabled=false

        binding.nextBtn.setOnClickListener {
                for (i in storage_uri.indices) {
                    val media_request = MediaUrl(
                        mediaUrl = storage_uri[i]//파이어베이스 storage에 등록된 경로
                    )
                    Log.d("마지막", "스토리지 Uri:" +storage_uri[i])
                    media.add(media_request)
                }
                val postRequest = PostFeedRequest(
                    userNickNameIdx = ApplicationClass.sSharedPreferences.getInt(
                        "userNickNameIdx",
                        0
                    ),
                    mediaList = media,
                    mediaIdx = media.size,
                    caption = binding.captionEdit.text.toString(),
                    feedCreateDate = mDate!!,
                    feedUpdateDate = mDate!!
                )
                showLoadingDialog(this)
                AddFeedService(this).tryPostFeed(postRequest)
        }

    }

    override fun onPostFeedSuccess(response: FeedResponse) {
        dismissLoadingDialog()
        response.message.let{
            Log.d("마지막", "피드생성완료" + it)
        }
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onPostFeedFailure(message: String) {
       dismissLoadingDialog()
        Log.d("마지막", message)
    }

    fun uploadImage(){
        for(i in imgArr.indices) {
            Log.d("마지막","이미지:"+imgArr[i]+"인덱스:"+i)
            //image(imgArr[i])
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            var fileN=imgArr[i].split("/").last()
            val split=fileN.split(".")
            fileN=split.first()+timestamp.split('.').first()+"."+split.last()

            val imageFile = "Image_"+timestamp+"_.jpg"
            val storageRef = storage?.reference?.child("media")?.child(fileN)
            storageRef?.putFile(Uri.parse(imgArr[i])!!)?.addOnSuccessListener {
                Log.d("마지막", "마지막 파이어베이스 업로드완료:"+i)
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    storage_uri.add(uri.toString())
                    Log.d("마지막", uri.toString())
                    //Log.d("마지막:",storage_uri[0])
                }
            }
        }
        Log.d("마지막","파이어베이스 완료되었습니다.")
    }
    fun image(image:String){
        Log.d("마지막","함수안에 들어왔습니다:"+image)
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFile = "Image_"+timestamp+"_.jpg"
        val storageRef = storage?.reference?.child("media")?.child(imageFile)
        storageRef?.putFile(Uri.parse(image))?.addOnSuccessListener {
            Log.d("마지막", "마지막 파이어베이스 업로드완료:")
            storageRef.downloadUrl.addOnSuccessListener { uri ->
                storage_uri.add(uri.toString())
                Log.d("마지막", uri.toString())
                //Log.d("마지막:",storage_uri[0])
            }
        }

    }
    inner class ThreadClass:Thread(){
        override fun run() {
            super.run()
            uploadImage()//파이어베이스에 사진 업로드
            Log.d("마지막","쓰레드 확인중:"+storage_uri.size.toString())
            while (isRunning) {
                if (storage_uri.size == imgArr.size) {//다업로드 되면
                    binding.nextBtn.isEnabled = true
                }
            }
        }
    }

}