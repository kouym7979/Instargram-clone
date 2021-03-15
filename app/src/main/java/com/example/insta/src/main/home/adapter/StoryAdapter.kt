package com.example.insta.src.main.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.insta.R
import com.example.insta.src.main.home.models.story_data


class StoryAdapter(val context: Context, feedArr: MutableList<story_data>) :
    RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    private val items: MutableList<story_data> = feedArr

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.story_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var profileUrl= items[position].userProfilePicture
        if(profileUrl!!.contains("alt=")){
            profileUrl=profileUrl.split("alt=").first()
            profileUrl=profileUrl.substring(0,profileUrl.length-1)
            profileUrl+="?alt=media"
            Log.d("Url",profileUrl)
        }
        holder.user_picture.scaleType = ImageView.ScaleType.CENTER_CROP

        Glide.with(context).load(profileUrl).apply(
            RequestOptions().circleCrop()
        ).into(holder.user_picture)

        holder.user_nick.text=items[position].userNickName

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val user_picture =itemView.findViewById<ImageView>(R.id.user_story_img)
        val user_nick=itemView.findViewById<TextView>(R.id.user_story_nick)


    }

}