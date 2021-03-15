package com.example.insta.src.main.search.adapter

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
import com.example.insta.src.main.search.models.Search_data


class searchAdapter(val context: Context, userArr:MutableList<Search_data>): RecyclerView.Adapter<searchAdapter.ViewHolder>() {

   private var items:MutableList<Search_data> =userArr
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.search_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {




         holder.user_nick.setText(items[position].userNickName)


         holder.user_info.setText(items[position].userIntroduce)

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



    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        var user_img =itemView.findViewById<ImageView>(R.id.user_search_img)
        var user_nick = itemView.findViewById<TextView>(R.id.user_serach_nick)
        var user_info =itemView.findViewById<TextView>(R.id.user_search_name)

    }

}