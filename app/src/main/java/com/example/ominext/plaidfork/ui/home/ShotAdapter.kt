package com.example.ominext.plaidfork.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ominext.plaidfork.R
import com.example.ominext.plaidfork.data.model.Shot

/**
 * Created by Ominext on 6/13/2017.
 */

const val TYPE_ITEM: Int = 1
const val TYPE_LOADING: Int = 2

class ShotAdapter(var list: ArrayList<Any>, val itemClick: (position: Int) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): RecyclerView.ViewHolder {
        if (p1 == TYPE_ITEM) {
            val view: View = LayoutInflater.from(p0?.context).inflate(R.layout.item_shot, p0, false)
            return ShotViewHolder(view, itemClick)
        }

        val view: View = LayoutInflater.from(p0?.context).inflate(R.layout.item_loading, p0, false)
        return LoadingViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder?, p1: Int) {
        if (getItemViewType(p1) == TYPE_ITEM) {
            (p0 as ShotViewHolder).bindData(list[p1] as Shot)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position] is Shot) TYPE_ITEM else TYPE_LOADING
    }
}