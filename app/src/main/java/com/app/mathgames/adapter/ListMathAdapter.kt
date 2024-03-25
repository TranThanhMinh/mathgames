package com.app.mathgames.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.myapplication.R

class ListMathAdapter(val list: ArrayList<String>) :
    RecyclerView.Adapter<ListMathAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_math, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_math.text = list[position]
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_math: TextView

        init {
            tv_math = view.findViewById(R.id.tv_math) as TextView
        }
    }
}