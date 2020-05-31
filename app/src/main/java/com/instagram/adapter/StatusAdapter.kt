package com.instagram.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.instagram.R
import com.instagram.data.InstaStatus

class StatusAdapter(val activity: Context, val statusList: ArrayList<InstaStatus>) : RecyclerView.Adapter<StatusAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0?.context).inflate(R.layout.adapter_status_layout, p0, false)
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
        return statusList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        if (p1 == 0) {
            p0.add.visibility = View.VISIBLE
        } else{
            p0.add.visibility = View.INVISIBLE
        }

        p0.name?.text = statusList[p1].name
        p0.name.visibility = View.VISIBLE

        Glide.with(activity)
            .load(statusList[p1].picture)
            .into(p0.photo)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.profile_name)
        val photo = itemView.findViewById<ImageView>(R.id.profile_img)
        val add = itemView.findViewById<ImageView>(R.id.ic_add_img)
    }
}