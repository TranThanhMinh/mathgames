package com.app.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.Question
import com.app.myapplication.R

class ListCountNumberAdapter(val context: Context,val list: ArrayList<String>) :
    RecyclerView.Adapter<ListCountNumberAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_count_number, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position].split(",")
        holder.imgView.setImageDrawable(context.resources.getDrawable(item[0].toInt()))
        holder.tv_math.text = item[1]
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_math: TextView
        val imgView: ImageView

        init {
            tv_math = view.findViewById(R.id.tv_math) as TextView
            imgView = view.findViewById(R.id.imgView) as ImageView
        }
    }
}