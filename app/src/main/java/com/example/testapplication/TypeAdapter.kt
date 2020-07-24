package com.example.testapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class TypeAdapter(val context: Context, val title: ArrayList<String>) : RecyclerView.Adapter<TypeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeAdapter.ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.custom_list,parent,false)
        return TypeAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = title.size

    override fun onBindViewHolder(holder: TypeAdapter.ViewHolder, position: Int) {
        val type = title[position]
        if (type.toLowerCase(Locale.getDefault()) == "grass") {
            holder.text_title.background = context.getDrawable(R.drawable.grass_background)
        }else if(type.toLowerCase(Locale.getDefault()) == "water"){
            holder.text_title.background = context.getDrawable(R.drawable.water_background)
        }else if(type.toLowerCase(Locale.getDefault()) == "fire"){
            holder.text_title.background = context.getDrawable(R.drawable.fire_background)
        }else if(type.toLowerCase(Locale.getDefault()) == "bug"){
            holder.text_title.background = context.getDrawable(R.drawable.bug_background)
        }else if(type.toLowerCase(Locale.getDefault()) == "poison"){
            holder.text_title.background = context.getDrawable(R.drawable.poison_background)
        }else if(type.toLowerCase(Locale.getDefault()) == "psychic"){
            holder.text_title.background = context.getDrawable(R.drawable.psychic_background)
        }else if(type.toLowerCase(Locale.getDefault()) == "dragon"){
            holder.text_title.background = context.getDrawable(R.drawable.dragon_background)
        }else if(type.toLowerCase(Locale.getDefault()) == "flying"){
            holder.text_title.background = context.getDrawable(R.drawable.flying_background)
        }else if(type.toLowerCase(Locale.getDefault()) == "electric"){
            holder.text_title.background = context.getDrawable(R.drawable.electric_background)
        }else{
            holder.text_title.background = context.getDrawable(R.drawable.flying_background)
        }
        holder.text_title.text = type
    }
    class ViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val text_title:TextView = view.findViewById(R.id.textView_type)


    }
}