 package com.example.insta.src.main.comment.adapter

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.util.SparseBooleanArray
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.insta.R
import com.example.insta.config.ApplicationClass
import com.example.insta.src.main.comment.models.Comment
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CommentRecycler(val context: Context, feedArr: MutableList<Comment>,handler:EventHandler) :
    RecyclerView.Adapter<CommentRecycler.ViewHolder>(){
    private val handler: EventHandler? = handler


    private val items: MutableList<Comment> = feedArr
    var count:Int=0
    private var mSellectedItems :SparseBooleanArray=SparseBooleanArray(0)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.comment_recycler_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.commentImg.scaleType = ImageView.ScaleType.CENTER_CROP

        holder.comment_layout.setOnClickListener {
            holder.comment_layout.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.select_del
                )
            )
            count++
            mSellectedItems!!.get(position, true)
        }

            holder.comment_layout.setOnClickListener {//작성자가 본인의 글인경우에만 가능
                if(ApplicationClass.sSharedPreferences.getInt("userNickNameIdx", 0)==items[position].userNickNameIdx) {
                    if (count == 0) {
                        if (mSellectedItems!!.get(position, true)) {//선택안되었으
                            holder.comment_layout.setBackgroundColor(
                                ContextCompat.getColor(
                                    context,
                                    R.color.select_del
                                )
                            )
                            count++
                            mSellectedItems!!.get(position, false)
                        }
                        handler?.handle(items[position].commentIdx, true)

                    } else {
                        if (mSellectedItems!!.get(position, false)) {
                            holder.comment_layout.setBackgroundColor(
                                ContextCompat.getColor(
                                    context,
                                    R.color.white
                                )
                            )
                            count--
                            mSellectedItems!!.get(position, true)
                        }

                        Log.d("확인", count.toString() + "몇번 눌렸습니다")
                        val custom_view = View.inflate(context, R.layout.custom_toast, null)
                        //custom_view.setBackgroundResource(android.R.drawable.toast_frame)
                        val toast = Toast(context)
                        custom_view.findViewById<TextView>(R.id.custom_toast)
                            .setText("댓글은 한 번에 1개까지만 선택할 수 있습니다.")
                        toast.view = custom_view
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.duration = Toast.LENGTH_SHORT
                        toast.show()
                    }
                }

            }

        var profileUrl=items[position].userProfilePicture
        if(profileUrl!!.contains("alt=")){
            profileUrl=profileUrl.split("alt=").first()
            profileUrl=profileUrl.substring(0, profileUrl.length - 1)
            profileUrl+="?alt=media"
            Log.d("Url", profileUrl.toString())
        }

        if (!items[position].userProfilePicture.equals("")) {
            Glide.with(context).load(profileUrl)
                .error(Glide.with(holder.commentImg).load(R.drawable.no_img))
                .apply(RequestOptions().circleCrop())
                .into(holder.commentImg)
        }
        else holder.commentImg.setImageResource(R.drawable.no_img)

        //타임존-> 우리의 시간방식으로 변환하는 방법
        val now = LocalDateTime.now()
        val uploaded = LocalDateTime.parse(items[position].commentCreateDate, DateTimeFormatter.ISO_DATE_TIME)
        var time = Duration.between(uploaded, now).seconds-32400
        Log.d("확인", items[position].commentCreateDate)
        Log.d("확인", "변형된 시간입니다:" + time)


        val day=time/86400
        if(day>0) {
            time -= day * 86400
            holder.comment_time.text=day.toString()+"일 전"
        }
        else {
            val hour = time / 3600
            if (hour > 0) {
                holder.comment_time.text=hour.toString()+"시간"
                time -= hour * 3600
            }
            else{
                val minute = time / 60
                if (minute > 0) {
                    time -= minute * 60
                    holder.comment_time.text=minute.toString()+"분"
                }
                else{
                    val second = time
                    holder.comment_time.text=second.toString()+"초"
                }
            }
        }


       // val content:String=items[position].userNickName+" "+items[position].commmentText
        //val spannableString = SpannableString(content)
//        spannableString.setSpan( ForegroundColorSpan(Color.parseColor("000000")), 0, items[position].userNickName.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        holder.comment_nick.text=items[position].userNickName+" "+items[position].commmentText

        //val SimpleDateFormat("yyyy-MM-dd-hh-mm")
        //holder.comment_nick.text=items[position].userNickName+" "+items[position].commmentText

        //holder.comment_text.text=items[position].commmentText
        //holder.comment_time.text=items[position].commentCreateDate
        //TODO 댓글 좋아요가 추가되면 좋아요 개수가 0개 초과일경우 나타나는 방식으로 구현
        if(items[position].commentlikecount>0)
            holder.comment_like_count.text="좋아요"+items[position].commentlikecount.toString()+"개"
        else
            holder.comment_like_count.text=""

    }

    override fun getItemCount(): Int {
       return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val commentImg=itemView.findViewById<ImageView>(R.id.comment_img)
       val comment_nick=itemView.findViewById<TextView>(R.id.comment_nick)
        val comment_text=itemView.findViewById<TextView>(R.id.comment_text)
        val comment_time=itemView.findViewById<TextView>(R.id.comment_time)
        val comment_like_count=itemView.findViewById<TextView>(R.id.comment_like_count)
        val comment_child=itemView.findViewById<TextView>(R.id.comment_child)
        val comment_like_btn=itemView.findViewById<ImageView>(R.id.comment_like_btn)
        val comment_layout=itemView.findViewById<View>(R.id.comment_item_layout)
    }

    interface EventHandler {
        fun  handle(position: Int,check:Boolean) // if u need know position. If no, just create method without params
    }

}