package com.example.ominext.plaidfork.ui.detail.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.ominext.plaidfork.data.model.Comment
import com.example.ominext.plaidfork.extension.load
import com.example.ominext.plaidfork.util.DateUtils
import com.example.ominext.plaidfork.util.LinkUtils
import kotlinx.android.synthetic.main.item_comment.view.*

/**
 * Created by Admin on 15-Jun-17.
 */

class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindData(comment: Comment) {
        itemView.ivAvatar.load(comment.user.avatar, rounded = true)
        itemView.tvName.text = comment.user.name
        LinkUtils.setTextWithLinks(itemView.tvContent, comment.body)
        itemView.tvTime.text = DateUtils.parse(comment.createdTime)
    }
}
