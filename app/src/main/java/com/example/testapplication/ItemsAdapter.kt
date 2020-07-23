package com.example.testapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapplication.internet.database.Pokemon
import com.squareup.picasso.Picasso

class ItemsAdapter (val items:List<Pokemon>,val context:Context,val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.items_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
//        Picasso.get().load(item.imageUrl).into(holder.imageView);
        holder.imageView.transitionName = "image_${item.id}"
        val imageUrl :String="https://pokeres.bastionbot.org/images/pokemon/${item.id}.png"
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder_image)
            .into(holder.imageView);
        holder.text_title.text = item.name
        holder.text_price.text = "# "+item.id.toString()

        holder.itemView.setOnClickListener {
            onItemClickListener.onClickedItem(it,position)
        }

    }

    class ViewHolder(val view: View) :RecyclerView.ViewHolder(view){
        val imageView:ImageView = view.findViewById(R.id.imageView)
        val text_title:TextView = view.findViewById(R.id.textView_title)
        val text_price:TextView = view.findViewById(R.id.textView_number)


    }
}