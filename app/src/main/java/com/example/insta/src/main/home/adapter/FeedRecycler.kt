package com.example.insta.src.main.home.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.util.SparseBooleanArray
import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.insta.R
import com.example.insta.config.ApplicationClass
import com.example.insta.src.main.comment.CommentActivity
import com.example.insta.src.main.comment.adapter.CommentRecycler
import com.example.insta.src.main.home.feedRcycler.FeedService
import com.example.insta.src.main.home.feedRcycler.FeedView
import com.example.insta.src.main.home.models.*
import com.example.insta.src.main.home.updateFeed.UpdatefeedActivity
import com.example.insta.util.LoadingDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.events.EventHandler
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class FeedRecycler(val context: Context, feedArr: MutableList<Feed_data>,handler: EventHandler) :
    RecyclerView.Adapter<FeedRecycler.ViewHolder>(), FeedView {
    private val handler: EventHandler? = handler

    private var idx:Int?=null
    private val dialog = Dialog(context)
    private val items: MutableList<Feed_data> = feedArr
    private lateinit var mLoadingDialog: LoadingDialog
    private val bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.feed_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val imageArr = items[position].mediaURL.split(",")
        Log.d("이미지","이미지 배열:"+imageArr[0])

        idx=position
        var Url=items[position].mediaURL
        if(Url.contains("alt=")){
            Url=Url.split("alt=").first()
            Url=Url.substring(0,Url.length-1)
            Url+="?alt=media"
            Log.d("Url",Url.toString())
        }
        var profileUrl=items[position].userProfilePicture
        if(profileUrl!!.contains("alt=")){
            profileUrl=profileUrl.split("alt=").first()
            profileUrl=profileUrl.substring(0,profileUrl.length-1)
            profileUrl+="?alt=media"
            Log.d("Url",profileUrl.toString())
        }
        holder.user_img.scaleType=ImageView.ScaleType.CENTER_CROP
        if (!items[position].userProfilePicture.equals("")) {
            Glide.with(context).load(profileUrl)
                .error(Glide.with(holder.user_img).load(R.drawable.no_img))
                .apply(RequestOptions().circleCrop())
                .into(holder.user_img)
        }

        holder.feed_caption.text = items[position].caption
        holder.user_nick.text = items[position].userNickName


        holder.feed_viewpager.adapter=ViewAdapter(context,imageArr)

        holder.feed_like_count.text = items[position].likeCount.toString()+"개"
        holder.user_edit_img.scaleType=ImageView.ScaleType.CENTER_CROP
        Glide.with(context).load(profileUrl).apply(
            RequestOptions().circleCrop()
        ).into(holder.user_edit_img)
        holder.user_edit_nick.text = items[position].userNickName
        holder.feed_like_count.text = items[position].likeCount.toString() + "개"

        holder.text_edit.setOnClickListener {
            handler?.handle(position, true)
        }
        //댓글 보기
        if(!items[position].commentCount.equals(""))
            holder.comment_count.text="댓글 "+items[position].commentCount+"개 모두 보기"
        else{//댓글이 없을경우
            holder.comment_count.text=""
        }
        holder.comment_count.setOnClickListener {
            val intent =Intent(context,CommentActivity::class.java)
            intent.putExtra("postnick",items[position].userNickName)
            intent.putExtra("postuserimg",items[position].userProfilePicture)
            intent.putExtra("caption",items[position].caption)
            intent.putExtra("feedidx",items[position].feedIdx)
            this.context.startActivity(intent)
        }

        val now = LocalDateTime.now()
        val uploaded = LocalDateTime.parse(items[position].feedCreateDate, DateTimeFormatter.ISO_DATE_TIME)
        var time = Duration.between(uploaded, now).seconds-32400

        //좋아요버튼
        //mLoadingDialog = LoadingDialog(context)
        //mLoadingDialog.show()
        //FeedService(this).tryGetLike(items[position].feedIdx)



        holder.feed_like_btn.setOnClickListener {
           if(ApplicationClass.mSellectedLike!!.get(position,true))
           {
               holder.feed_like_btn.setImageResource(R.drawable.like_btn_large)
               mLoadingDialog = LoadingDialog(context)
               mLoadingDialog.show()
               val postLike = FeedLikeRequest(
                   userNickNameIdx = ApplicationClass.sSharedPreferences.getInt("userNickNameIdx",0)
               )

               FeedService(this).tryPostLike(items[position].feedIdx,postLike)
               ApplicationClass.mSellectedLike!!.get(position,false)
           }

            else{
               ApplicationClass.mSellectedLike!!.get(position,true)
               holder.feed_like_btn.setImageResource(R.drawable.like_btn)
            }


            //handler?.handle(position, true)
        }

        val day=time/86400
        if(day>0) {
            time -= day * 86400
            holder.upload_date.text=day.toString()+"일 전"
        }
        else {
            val hour = time / 3600
            if (hour > 0) {
                holder.upload_date.text=hour.toString()+"시간"
                time -= hour * 3600
            }
            else{
                val minute = time / 60
                if (minute > 0) {
                    time -= minute * 60
                    holder.upload_date.text=minute.toString()+"분"
                }
                else{
                    val second = time
                    holder.upload_date.text=second.toString()+"초"
                }
            }
        }


        holder.more_btn.setOnClickListener {
            //작성자가 본인인지 확인하는 코드 필요!
            if (ApplicationClass.sSharedPreferences.getInt("userNickNameIdx", 0) != items[position].userNicknameIdx) {
                val view =
                    LayoutInflater.from(context).inflate(R.layout.modal_bottom_sheet_layout, null)
                view.findViewById<Button>(R.id.btn1)
                    .setOnClickListener { bottomSheetDialog.dismiss() }
                bottomSheetDialog.setContentView(view)
                bottomSheetDialog.show()
            } else {//본인일경우
                val view =
                    LayoutInflater.from(context).inflate(R.layout.my_bottom_sheet_layout, null)
                view.findViewById<Button>(R.id.btn1)
                    .setOnClickListener { bottomSheetDialog.dismiss() }
                view.findViewById<Button>(R.id.btn_del).setOnClickListener {
                    delete_Dialog(items[position].feedIdx)
                }
                view.findViewById<Button>(R.id.btn_update).setOnClickListener {
                    val intent = Intent(context, UpdatefeedActivity::class.java)
                    intent.putExtra("feedIdx", items[position].feedIdx)
                    intent.putExtra("userProfile", items[position].userProfilePicture)
                    intent.putExtra("userNick", items[position].userNickName)
                    intent.putExtra("caption", items[position].caption)
                    intent.putExtra("mediaUrl", items[position].mediaURL)
                    this.context.startActivity(intent)
                    bottomSheetDialog.dismiss()
                }

                bottomSheetDialog.setContentView(view)
                bottomSheetDialog.show()
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var user_img = itemView.findViewById<ImageView>(R.id.user_img)
        var user_nick = itemView.findViewById<TextView>(R.id.user_nick)
        var more_btn = itemView.findViewById<ImageView>(R.id.more_btn)
        var feed_like_btn = itemView.findViewById<ImageButton>(R.id.feed_like_btn)
        var feed_comment_btn = itemView.findViewById<ImageButton>(R.id.feed_comment_btn)
        var feed_direct_btn = itemView.findViewById<ImageButton>(R.id.feed_direct_btn)
        var feed_collect_btn = itemView.findViewById<ImageButton>(R.id.feed_collect_btn)
        var feed_like_count = itemView.findViewById<TextView>(R.id.like_count)
        var feed_caption = itemView.findViewById<TextView>(R.id.feed_caption)
        var user_edit_img = itemView.findViewById<ImageView>(R.id.post_userimg)
        var user_edit_nick = itemView.findViewById<TextView>(R.id.post_usernick)
        var text_edit=itemView.findViewById<TextView>(R.id.comment_edit)

        var comment_count=itemView.findViewById<TextView>(R.id.comment_count)
        var upload_date=itemView.findViewById<TextView>(R.id.upload_time)
        var feed_viewpager=itemView.findViewById<ViewPager2>(R.id.feed_view)
    }

    override fun onDeleteFeedSuccess(response: FeedDeleteResponse) {
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
            dialog.dismiss()
            bottomSheetDialog.dismiss()
        }
        response.message.let {
            Log.d("확인", it.toString())

        }
    }

    override fun onDeleteFeedFailure(message: String) {
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
        }
        Log.d("확인", message)
    }

    override fun onPostLikeSuccess(response: FeedLikeResponse) {
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
            //notifyDataSetChanged()
        }
        response.message.let {
            Log.d("확인", it.toString())

        }
    }

    override fun onPostLikeFailure(message: String) {
        Log.d("확인", message)
    }

    override fun onGetLikeSuccess(response: FeedLikeInqueryResponse) {
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
            notifyDataSetChanged()
        }

    }

    override fun onGetLikeFailure(message: String) {
        TODO("Not yet implemented")
    }

    fun delete_Dialog(idx: Int) {
        dialog.setContentView(R.layout.delete_dialog)
        dialog.window!!.setBackgroundDrawableResource(R.drawable.dialog_shape)
        dialog.window!!.setLayout(
            700,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)

        val delete_btn = dialog.findViewById<Button>(R.id.delete_btn)
        delete_btn.setOnClickListener {
            Log.d("확인", "삭제중입니다: " + idx)
            mLoadingDialog = LoadingDialog(context)
            mLoadingDialog.show()
            FeedService(this).tryDeleteFeed(idx)
        }
        val save_btn = dialog.findViewById<Button>(R.id.save_btn)
        save_btn.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }
    interface EventHandler {
        fun  handle(position: Int,check:Boolean) // if u need know position. If no, just create method without params
    }
}