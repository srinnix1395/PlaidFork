package com.example.ominext.plaidfork.extension

import android.annotation.TargetApi
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.graphics.Palette
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.example.ominext.plaidfork.widget.ShotTarget


/**
 * Created by Admin on 14-Jun-17.
 */

fun ImageView.load(resource: Any?,
                   rounded: Boolean = false,
                   animate: Boolean = true,
                   placeHolder: Any? = null,
                   error: Any? = null,
                   asBitmap: Boolean = false,
                   asGif: Boolean = false,
                   thumbnail: Float = 0f,
                   fitCenter: Boolean = false,
                   centerCrop: Boolean = false,
                   diskCacheStrategy: DiskCacheStrategy? = null,
                   generatePalette: Boolean = false,
                   callback: ((Bitmap) -> Unit)? = null, paletteListener: ((palette: Palette) -> Unit)? = null) {

    val request = Glide.with(context).load(resource)

    if (!animate) {
        request.dontAnimate()
    }

    if (asBitmap) {
        request.asBitmap()
    }

    if (asGif) {
        request.asGif()
    }

    if (fitCenter) {
        request.fitCenter()
    }

    if (centerCrop) {
        request.centerCrop()
    }

    if (thumbnail != 0f) {
        request.thumbnail(thumbnail)
    }

    diskCacheStrategy?.apply {
        request.diskCacheStrategy(diskCacheStrategy)
    }

    placeHolder?.apply {
        if (placeHolder is Drawable) {
            request.placeholder(placeHolder)
        } else if (placeHolder is Int) {
            request.placeholder(placeHolder)
        }
    }

    error?.apply {
        if (error is Drawable) {
            request.error(error)
        } else if (error is Int) {
            request.error(error)
        }
    }

    request.into(object : ShotTarget(this, generatePalette) {
        override fun onPreProcess(resource: GlideDrawable, animation: GlideAnimation<in GlideDrawable>?) {
            super.onPreProcess(resource, animation)

            val bitmap = (resource as GlideBitmapDrawable).bitmap
            if (rounded) {
                val roundedDrawable = RoundedBitmapDrawableFactory.create(resources, bitmap)
                roundedDrawable.isCircular = true
                this@load.setImageDrawable(roundedDrawable)
            } else {
                this@load.setImageBitmap(bitmap)
            }
            callback?.invoke(bitmap)
        }

        override fun onGeneratedPalette(palette: Palette) {
            super.onGeneratedPalette(palette)
            paletteListener?.invoke(palette)
        }
    })
}

fun ImageView.loadAnimated(resource: Any?,
                           animate: Boolean = true,
                           placeHolder: Any? = null,
                           error: Any? = null,
                           asBitmap: Boolean = false,
                           asGif: Boolean = false,
                           thumbnail: Float = 0f,
                           fitCenter: Boolean = false,
                           centerCrop: Boolean = false,
                           autoPlay: Boolean = true,
                           generatePalette: Boolean = false,
                           diskCacheStrategy: DiskCacheStrategy? = null,
                           callback: ((resource: GlideDrawable, animation: GlideAnimation<in GlideDrawable>?) -> Unit)? = null, paletteListener: ((palette: Palette) -> Unit)? = null) {

    val request = Glide.with(context).load(resource)

    if (!animate) {
        request.dontAnimate()
    }

    if (asBitmap) {
        request.asBitmap()
    }

    if (asGif) {
        request.asGif()
    }

    if (fitCenter) {
        request.fitCenter()
    }

    if (centerCrop) {
        request.centerCrop()
    }

    if (thumbnail != 0f) {
        request.thumbnail(thumbnail)
    }

    diskCacheStrategy?.apply {
        request.diskCacheStrategy(diskCacheStrategy)
    }

    placeHolder?.apply {
        if (placeHolder is Drawable) {
            request.placeholder(placeHolder)
        } else if (placeHolder is Int) {
            request.placeholder(placeHolder)
        }
    }

    error?.apply {
        if (error is Drawable) {
            request.error(error)
        } else if (error is Int) {
            request.error(error)
        }
    }


    request.into(object : ShotTarget(this, generatePalette) {
        override fun onPreProcess(resource: GlideDrawable, animation: GlideAnimation<in GlideDrawable>?) {
            super.onPreProcess(resource, animation)
            if (!autoPlay) {
                resource.stop()
            }
        }

        override fun onPostProcess(resource: GlideDrawable, animation: GlideAnimation<in GlideDrawable>?) {
            super.onPostProcess(resource, animation)
            callback?.invoke(resource, animation)
        }

        @TargetApi(Build.VERSION_CODES.M)
        override fun onGeneratedPalette(palette: Palette) {
            super.onGeneratedPalette(palette)
            paletteListener?.invoke(palette)
        }
    })
}

