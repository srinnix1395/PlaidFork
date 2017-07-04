package com.example.ominext.plaidfork.ui.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ominext.plaidfork.R
import com.example.ominext.plaidfork.data.model.Comment
import com.example.ominext.plaidfork.ui.home.LoadingViewHolder

/**
 * Created by Admin on 15-Jun-17.
 */

class CommentAdapter(val comments: ArrayList<Comment?>) : RecyclerView.Adapter<RecyclerView
.ViewHolder>() {

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, position: Int) {
        if (viewHolder is CommentViewHolder) {
            viewHolder.bindData(comments[position]!!)
        }
    }

    override fun getItemCount(): Int = comments.size

    override fun onCreateViewHolder(viewGroup: ViewGroup?, position: Int): RecyclerView.ViewHolder {
        if (comments[position] != null) {
            return CommentViewHolder(LayoutInflater.from(viewGroup?.context).inflate(R.layout.item_comment,
                    viewGroup, false))
        }
        return LoadingViewHolder(LayoutInflater.from(viewGroup?.context).inflate(R.layout.item_loading,
                viewGroup, false))
    }
}