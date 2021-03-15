package com.example.insta.src.main.profile.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.insta.R
import com.example.insta.src.main.profile.models.feed_data


class ProfileFeedAdapter(val context: Context, feedArr: MutableList<feed_data>) :
    RecyclerView.Adapter<ProfileFeedAdapter.ViewHolder>() {

    private var items : MutableList<feed_data> =feedArr

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileFeedAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.profile_feed_item ,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileFeedAdapter.ViewHolder, position: Int) {

        holder.feedimg.scaleType = ImageView.ScaleType.CENTER_CROP
        Glide.with(context).load(Uri.parse(items[position].mediaURL))
            .error(
                Glide.with(holder.feedimg)
                    .load(Uri.parse("https://firebasestorage.googleapis.com/v0/b/instaclone-da340.appspot.com/o/images%2FImage_20210215_150759_.jpg?alt=media&token=58f40a2f-3076-4dcd-b659-bdbca1f69621"))
            )
            .into(holder.feedimg)

    }

    override fun getItemCount(): Int {
       return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        var feedimg=view.findViewById<ImageView>(R.id.feed_img)
        val multi_img=view.findViewById<ImageView>(R.id.multi_img)


    }
}