package com.example.insta.src.main.feed.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.insta.R

class RecyclerAdapter(val context: Context, uriArr:ArrayList<String>,val checked:Boolean):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var items = ArrayList<String>()
    init {
        this.items=uriArr
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_img,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val bitmap = BitmapFactory.decodeFile(items[position])
        holder.view.setImageBitmap(bitmap)
        val display = context.resources.displayMetrics
        val deviceWidth=display.widthPixels
        val deviceHeight=display.heightPixels
        holder.view.layoutParams.height=deviceHeight/4
        holder.view.layoutParams.width=deviceWidth/4
        holder.view.requestLayout()
           // GridLayout.(display.widthPixels/4,display.heightPixels/4)
        if(checked==true)
        {
            holder.check.visibility=View.VISIBLE
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        var view =itemView.findViewById<ImageView>(R.id.gallery_img)
        var check= itemView.findViewById<TextView>(R.id.view_text)
    }
}