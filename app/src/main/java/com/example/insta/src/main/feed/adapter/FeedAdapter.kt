package com.example.insta.src.main.feed.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import org.jetbrains.anko.padding

class FeedAdapter(val context: Context, uriArr:ArrayList<String>):BaseAdapter() {

    private var items = ArrayList<String>()
    init {
        this.items=uriArr
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
       return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView = ImageView(context)
        val display = context.resources.displayMetrics
        imageView.padding=2
        imageView.scaleType=ImageView.ScaleType.CENTER_CROP
        imageView.layoutParams=LinearLayout.LayoutParams(display.widthPixels/4,display.heightPixels/4)
        Glide.with(context).load(items[position]).into(imageView)


        return imageView
    }

}