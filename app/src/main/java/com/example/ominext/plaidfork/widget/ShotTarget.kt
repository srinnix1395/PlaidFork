package com.example.ominext.plaidfork.widget

import android.support.v7.graphics.Palette
import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget

/**
 * Created by Ominext on 7/5/2017.
 */

open class ShotTarget(imageView: ImageView, val generatePalette: Boolean) :
        GlideDrawableImageViewTarget(imageView), Palette.PaletteAsyncListener {

    override fun onResourceReady(resource: GlideDrawable, animation: GlideAnimation<in GlideDrawable>?) {
        super.onResourceReady(resource, animation)
        onPreProcess(resource, animation)

        if (generatePalette) {
            when (resource) {
                is GlideBitmapDrawable -> {
                    Palette.from(resource.bitmap)
                            .clearFilters()
                            .generate(this)
                }
                is GifDrawable -> {
                    Palette.from(resource.firstFrame)
                            .clearFilters()
                            .generate(this)
                }
            }
        }

        onPostProcess(resource, animation)
    }

    open fun onPostProcess(resource: GlideDrawable, animation: GlideAnimation<in GlideDrawable>?) {

    }

    open fun onPreProcess(resource: GlideDrawable, animation: GlideAnimation<in GlideDrawable>?) {

    }

    open fun onGeneratedPalette(palette: Palette) {

    }

    override fun onGenerated(palette: Palette) {
        onGeneratedPalette(palette)
    }
}