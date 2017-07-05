package com.example.ominext.plaidfork.ui.home.adapter

import android.annotation.TargetApi
import android.graphics.drawable.TransitionDrawable
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.example.ominext.plaidfork.R
import com.example.ominext.plaidfork.data.model.Shot
import com.example.ominext.plaidfork.extension.load
import com.example.ominext.plaidfork.extension.loadAnimated
import com.example.ominext.plaidfork.util.createRipple
import kotlinx.android.synthetic.main.item_shot.view.*

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
/**
 * Created by Ominext on 6/13/2017.
 */
class ShotViewHolder(view: View, itemClick: (imageView: ImageView, position: Int) -> Unit) :
        RecyclerView.ViewHolder(view) {

    init {
        itemView.setOnClickListener { itemClick(itemView.imvContent, adapterPosition) }
        itemView.setOnTouchListener({ _: View?, event: MotionEvent? ->
            val action = event?.action
            if (action !in listOf(MotionEvent.ACTION_DOWN, MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL)) {
                return@setOnTouchListener false
            }

            val drawable = itemView.imvContent.drawable ?: return@setOnTouchListener false
            val gif: GifDrawable? = when (drawable) {
                is GifDrawable -> drawable
                is TransitionDrawable -> {
                    val transitionDrawable: TransitionDrawable = drawable
                    (0..transitionDrawable.numberOfLayers).find {
                        transitionDrawable.getDrawable(it) is GifDrawable
                    }?.let {
                        transitionDrawable.getDrawable(it) as GifDrawable
                    }
                }
                else -> return@setOnTouchListener false
            }

            when (action) {
                MotionEvent.ACTION_DOWN -> {
                    println("start")
                    gif?.start()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> gif?.stop()
            }
            return@setOnTouchListener false
        })
        itemView.imvContent.transitionName = "image_$adapterPosition"
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun bindData(shot: Shot) {
        if (shot.animate) {
            itemView.imvContent.loadAnimated(shot.images?.hidpi,
                    placeHolder = R.color.colorGray,
                    error = R.color.colorGray,
                    asGif = true,
                    autoPlay = false,
                    generatePalette = true,
                    diskCacheStrategy = DiskCacheStrategy.SOURCE,
                    paletteListener = {
                        itemView.foreground = createRipple(it, 0.25f, 0.5f,
                                ContextCompat.getColor(itemView.context, R.color.grayRipple), true)
                    })

            itemView.tvGif.visibility = View.VISIBLE
        } else {
            itemView.imvContent.load(shot.images?.normal,
                    placeHolder = R.color.colorGray,
                    error = R.color.colorGray,
                    generatePalette = true,
                    paletteListener = {
                        itemView.foreground = createRipple(it, 0.25f, 0.5f,
                                ContextCompat.getColor(itemView.context, R.color.grayRipple), true)
                    })

            itemView.tvGif.visibility = View.GONE
        }
    }
}