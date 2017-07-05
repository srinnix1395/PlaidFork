package com.example.ominext.plaidfork.util

import android.support.annotation.CheckResult
import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.support.annotation.IntRange

/**
 * Created by Ominext on 7/5/2017.
 */

@CheckResult @ColorInt fun modifyAlpha(@ColorInt color: Int,
                                       @FloatRange(from = 0.0, to = 1.0) alpha: Float): Int {
    return modifyAlpha(color, (255f * alpha).toInt())
}

@CheckResult @ColorInt fun modifyAlpha(@ColorInt color: Int,
                                       @IntRange(from = 0, to = 255) alpha: Int): Int {
    return color and 0x00ffffff or (alpha shl 24)
}