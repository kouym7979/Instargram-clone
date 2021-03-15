package com.example.insta.src.main.feed

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.insta.R
import com.example.insta.config.BaseActivity
import com.example.insta.databinding.ActivityAddfeedBinding
import com.example.insta.src.main.feed.adapter.FeedAdapter
import com.example.insta.src.main.feed.caption.AddcaptionActivity
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import kotlin.collections.ArrayList

class AddFeedActivity : BaseActivity<ActivityAddfeedBinding>(ActivityAddfeedBinding::inflate) {
    var storage : FirebaseStorage?=null
    private val REQUEST_READ_EXTERMAL_STORAGE = 1000
    val uriArr = ArrayList<String>()
    val selectArr =ArrayList<String>()
    var sub_img:String?=null
    private val check:Boolean =false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storage= FirebaseStorage.getInstance()

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_READ_EXTERMAL_STORAGE
                )
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_READ_EXTERMAL_STORAGE
                )
            }

        } else {
            // 권한이 이미 허용됨
            getAllPhotos()

        }

        binding.feedGridview.setOnItemClickListener { parent, view, position, id ->
            val bitmap = BitmapFactory.decodeFile(uriArr[position])
            selectArr.add("file://"+uriArr[position])
            Log.d("이미지","file://"+uriArr[position])
            sub_img=uriArr[position]

            binding.selectImg.setImageBitmap(bitmap)
        }
        binding.backBtn.setOnClickListener { finish() }
        binding.multiCheck.setOnClickListener {
            if(check==false){
                binding.multiCheck.setImageResource(R.drawable.multi_check_yes)

            }
            else{
                binding.multiCheck.setImageResource(R.drawable.multi_check)
            }
        }
        binding.nextBtn.setOnClickListener {
            //showLoadingDialog(this)
           // uploadImage()

            val intent = Intent(this,AddcaptionActivity::class.java)
            intent.putExtra("img",selectArr)
            intent.putExtra("sub",sub_img)
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_READ_EXTERMAL_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    getAllPhotos()
                } else {
                    showCustomToast("권한이 거부됨")
                }
                return
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun getAllPhotos() {
        //이미지 정렬 순서가 추가되어야함

        val projection = arrayOf(
            MediaStore.Images.ImageColumns._ID,
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,  //the album it in
            MediaStore.Images.ImageColumns.DATE_TAKEN,
            MediaStore.Images.ImageColumns.MIME_TYPE
        )

        var sortOrder: String? = null
        sortOrder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

            MediaStore.Images.ImageColumns.DATE_TAKEN + "DESC"
        } else {
            MediaStore.Images.ImageColumns.DATE_TAKEN + "COLLATE LOCALIZED DESC"
        }

        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null
        )


        if (cursor != null) {
            while (cursor.moveToNext()) {
                val uri =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                uriArr.add(uri)
            }

        }
        cursor!!.moveToFirst()
        if (cursor.moveToNext()) {
            Log.d("확인", "이미지 창")
            val latestImageUri = cursor.getString(1)
            val imageFile = File(latestImageUri)
            if (imageFile.exists()) {
                val bitmap = BitmapFactory.decodeFile(latestImageUri)
                binding.selectImg.setImageBitmap(bitmap)
            }
            Log.d("확인", "불러온이미지:" + latestImageUri)
            cursor.close()
        }

        val adapter = FeedAdapter(this, uriArr)
        binding.feedGridview.numColumns = 4
        binding.feedGridview.adapter = adapter

    }



}