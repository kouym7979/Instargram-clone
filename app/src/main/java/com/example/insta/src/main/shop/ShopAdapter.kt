package com.example.insta.src.main.shop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.insta.R


class ShopAdapter(private val context: Context):
    RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    var imgArr= arrayListOf<Int>(R.drawable.shop_image1,R.drawable.shop_image2,
    R.drawable.shop_image3,R.drawable.shop_image4,R.drawable.shop_image5)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopAdapter.ViewHolder =
        ShopAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.shop_pager_item, parent, false)
        )

    override fun onBindViewHolder(holder: ShopAdapter.ViewHolder, position: Int) {

       /* when(position){
            0->holder.imageUrl.setImageResource(R.drawable.shop_image1)
            1->holder.imageUrl.setImageResource(R.drawable.shop_image2)
            2->holder.imageUrl.setImageResource(R.drawable.shop_image3)
            3->holder.imageUrl.setImageResource(R.drawable.shop_image4)
            4->holder.imageUrl.setImageResource(R.drawable.shop_image5)
        }*/
        holder.imageUrl.setImageResource(imgArr[position])

    }

    override fun getItemCount(): Int =5

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageUrl: ImageView = view.findViewById(R.id.view_img)

    }
}