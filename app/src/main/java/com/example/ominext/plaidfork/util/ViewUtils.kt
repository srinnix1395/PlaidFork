package com.example.ominext.plaidfork.util

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.RippleDrawable
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.support.v7.graphics.Palette

/**
 * Created by Ominext on 7/5/2017.
 */

fun createRipple(palette: Palette,
                 @FloatRange(from = 0.0, to = 1.0) darkAlpha: Float,
                 @FloatRange(from = 0.0, to = 1.0) lightAlpha: Float,
                 @ColorInt fallbackColor: Int,
                 bounded: Boolean): RippleDrawable? {
    var rippleColor = fallbackColor
    // try the named swatches in preference order
    when {
        palette.vibrantSwatch != null -> rippleColor = modifyAlpha(palette.vibrantSwatch!!.rgb, darkAlpha)
        palette.lightVibrantSwatch != null -> rippleColor = modifyAlpha(palette.lightVibrantSwatch!!.rgb, lightAlpha)
        palette.darkVibrantSwatch != null -> rippleColor = modifyAlpha(palette.darkVibrantSwatch!!.rgb, darkAlpha)
        palette.mutedSwatch != null -> rippleColor = modifyAlpha(palette.mutedSwatch!!.rgb, darkAlpha)
        palette.lightMutedSwatch != null -> rippleColor = modifyAlpha(palette.lightMutedSwatch!!.rgb, lightAlpha)
        palette.darkMutedSwatch != null -> rippleColor = modifyAlpha(palette.darkMutedSwatch!!.rgb, darkAlpha)
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        return RippleDrawable(ColorStateList.valueOf(rippleColor), null,
                if (bounded) ColorDrawable(Color.WHITE) else null)
    } else {
        return null
    }
}