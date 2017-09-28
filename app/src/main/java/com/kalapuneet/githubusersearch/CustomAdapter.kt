package com.kalapuneet.githubusersearch

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var userList: Array<RestApiCall.User>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        var itemView: View  = LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if (userList != null) {
            return userList!!.size
        }
        return 0
    }

    fun setItems(users: Array<RestApiCall.User>?) {
        userList = users
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(user: RestApiCall.User) {
            val textViewName = itemView.findViewById<AppCompatTextView>(R.id.username)
            textViewName.text = user.login
        }
    }
}