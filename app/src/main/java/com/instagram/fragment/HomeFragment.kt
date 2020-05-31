package com.instagram.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

import com.instagram.R
import com.instagram.adapter.PostAdapter
import com.instagram.adapter.StatusAdapter
import com.instagram.data.InstaStatus
import com.instagram.data.Post

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, parent, false)

        val activity = activity as Context

        val instaStausList = view.findViewById<RecyclerView>(R.id.insta_status_list)
        val postViewList = view.findViewById<RecyclerView>(R.id.post_list)

        instaStausList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        postViewList.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        val statusJSON: String = activity.assets.open("status.json").bufferedReader().use { it.readText() }
        val postJSON: String = activity.assets.open("post.json").bufferedReader().use { it.readText() }

        val status = Gson().fromJson(statusJSON, Array<InstaStatus>::class.java)
        val post = Gson().fromJson(postJSON, Array<Post>::class.java)

        val statusList = ArrayList<InstaStatus>()
        val postList = ArrayList<Post>()

        for (i in 0 until status.size)
            statusList.add(InstaStatus(status[i].id, status[i].name, status[i].picture))

        for (j in 0 until post.size)
            postList.add(Post(post[j].id, post[j].name, post[j].logo, post[j].photo, post[j].likes, post[j].description))

        val statusAdapter = StatusAdapter(activity,statusList)
        instaStausList.adapter = statusAdapter

        val postAdapter = PostAdapter(activity, postList)
        postViewList.adapter = postAdapter

        return view
    }
}