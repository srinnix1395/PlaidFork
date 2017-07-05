package com.example.ominext.plaidfork.ui.home.adapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_loading.view.*

/**
 * Created by Ominext on 6/13/2017.
 */
class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    init {
        itemView.pbLoading.indeterminateDrawable.setColorFilter(Color.parseColor("#464646"), PorterDuff.Mode.SRC_ATOP)
    }
}