package com.example.insta.signup.photo

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.example.insta.config.BaseActivity
import com.example.insta.databinding.ActivityAddphotoBinding
import com.example.insta.signup.Final.FinalActivity
import com.google.firebase.storage.FirebaseStorage
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AddphotoActivity : BaseActivity<ActivityAddphotoBinding>(ActivityAddphotoBinding::inflate) {
    private var userName : String?=null
    private var userEmail : String?=null
    private var userPassword:String?=null
    private var userBirth: String?=null
    private var userNickName :String?=null
    private var userDiscloserScope :String?=null
    private var userProfile:Uri?=null
    val Gallery=0
    var storage : FirebaseStorage?=null
    var photoUri : Uri?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //ApplicationClass.storage
        storage= FirebaseStorage.getInstance()//파이어베이스 스토리지
        binding.jump.setOnClickListener {
            //프로필 사진 설정 안했을 경우
            var intent= Intent(this, FinalActivity::class.java)
            intent.putExtra("email",userEmail)
            //intent.putExtra("userProfilePicture",userProfile)
            intent.putExtra("name",userName)
            intent.putExtra("pwd",userPassword)
            intent.putExtra("birth",userBirth)
            intent.putExtra("nick",userNickName)
            intent.putExtra("scope",userDiscloserScope)
            Log.d("확인","최종 액티비티->이메일"+userEmail+"이름:"+userName+"비밀번호"+userPassword+"생일"+userBirth+"닉네임"+userNickName+"범위:"+userDiscloserScope
            +"프로필이미지:"+userProfile)
            startActivity(intent)
        }
        binding.addPhotoBtn.setOnClickListener { loadImage() }
    }

    override fun onStart() {
        super.onStart()
        if(intent.hasExtra("email")&&intent.hasExtra("name")&&intent.hasExtra("pwd")
            &&intent.hasExtra("birth") && intent.hasExtra("nick" )&&intent.hasExtra("scope"))
        {
            userBirth=intent.getStringExtra("birth")
            userName=intent.getStringExtra("name")
            userEmail=intent.getStringExtra("email")
            userPassword=intent.getStringExtra("pwd")
            userNickName=intent.getStringExtra("nick")
            userDiscloserScope=intent.getStringExtra("scope")
        }
    }

    private fun loadImage(){
        Log.d("확인","이미지 불러오는 중입니다.")
        val intent=Intent()
        intent.type="image/*"
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Load Picture"),Gallery)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("확인","result입니다")
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==Gallery){
            if(resultCode== RESULT_OK){
                    var dataUri=data?.data
                    photoUri=data?.data
                    try{
                        Log.d("확인","비트맵 변환전"+photoUri)
                        val bitmap: Bitmap =MediaStore.Images.Media.getBitmap(this.contentResolver,dataUri)
                        Log.d("확인","선택한 사진은"+bitmap)
                        Log.d("확인",dataUri.toString())

                        binding.profileImg.setImageBitmap(bitmap)
                    }catch (e:Exception){
                        Log.d("확인","이미지 업로드 오류"+e.toString())
                    }
                }
                else{
                    Log.d("확인","이미지 안됨")
                }
        }
        contentUpload()
    }

    fun contentUpload(){
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFile = "Image_"+timestamp+"_.jpg"
        val storageRef = storage?.reference?.child("media")?.child(imageFile)


        showLoadingDialog(this)
        storageRef?.putFile(photoUri!!)?.addOnSuccessListener {
            Log.d("photo","파이어베이스 업로드완료")
            storageRef.downloadUrl.addOnSuccessListener { uri->
                userProfile=uri
                Log.d("확인",uri.toString())
                Log.d("확인",uri.toString())
                val check:String=uri.toString()
                val check_uri:Uri = Uri.parse(check)
                Log.d("확인","문자열된값:"+check )
                Log.d("확인","Uri로 다시 변환된값:"+check_uri)
                dismissLoadingDialog()

                var intent= Intent(this, FinalActivity::class.java)
                intent.putExtra("email",userEmail)
                intent.putExtra("userProfilePicture",userProfile)
                intent.putExtra("name",userName)
                intent.putExtra("pwd",userPassword)
                intent.putExtra("birth",userBirth)
                intent.putExtra("nick",userNickName)
                intent.putExtra("scope",userDiscloserScope)
                Log.d("확인","최종 액티비티->이메일"+userEmail+"이름:"+userName+"비밀번호"+userPassword+"생일"+userBirth+"닉네임"+userNickName+"범위:"+userDiscloserScope
                        +"프로필이미지:"+userProfile)
                startActivity(intent)
            }
        }
    }
}