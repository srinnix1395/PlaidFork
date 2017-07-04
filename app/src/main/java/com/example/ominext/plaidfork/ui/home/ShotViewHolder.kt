package com.example.ominext.plaidfork.ui.home

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.ominext.plaidfork.R
import com.example.ominext.plaidfork.data.model.Shot
import com.example.ominext.plaidfork.extension.load
import com.example.ominext.plaidfork.extension.loadAnimated
import kotlinx.android.synthetic.main.item_shot.view.*

/**
 * Created by Ominext on 6/13/2017.
 */
class ShotViewHolder(view: View, itemClick: (position: Int) -> Unit) : RecyclerView.ViewHolder(view) {

    init {
        itemView.setOnClickListener { itemClick.invoke(adapterPosition) }
//        itemView.setOnTouchListener({ _: View?, event: MotionEvent? ->
//            val action = event?.action
//            when (action) {
//                !in listOf(ACTION_DOWN, ACTION_UP, ACTION_CANCEL) -> return@setOnTouchListener false
//            }
//
//            val drawable = itemView.imvContent.drawable ?: return@setOnTouchListener false
//            var gif: GifDrawable? = when (drawable) {
//                is GifDrawable -> drawable
//                else -> null
//            } ?: return@setOnTouchListener false
//
//            when (action) {
//                ACTION_DOWN -> {gif?.start()
//                }
//                ACTION_UP, ACTION_CANCEL -> {
//                    gif?.stop()
//                }
//            }
//            return@setOnTouchListener false
//        })
    }

    fun bindData(shot: Shot) {
        if (shot.animate) {
            itemView.imvContent.loadAnimated(shot.images?.normal, placeHolder = R.color
                    .colorGray, error = R.color.colorGray, asBitmap = true  )
        } else {
            itemView.imvContent.load(shot.images?.normal, placeHolder = R.color.colorGray, error = R.color.colorGray)
        }

        itemView.tvGif.visibility = if (shot.animate) View.VISIBLE else View.GONE
    }
}