package com.example.ominext.plaidfork.widget

import android.content.Context
import android.support.transition.TransitionManager
import android.support.v7.graphics.Palette
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

/**
 * Created by Admin on 14-Jun-17.
 */

class ColorPaletteView : LinearLayout {

    var clickListener: ((Int) -> Unit)? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        orientation = HORIZONTAL
    }

    fun setOnColorClickListener(listener: ((Int) -> Unit)?) {
        this.clickListener = listener
    }

    fun setSwatches(swatches: List<Palette.Swatch>?) {
        TransitionManager.beginDelayedTransition(this)
        val distinctSwatches = swatches?.distinct()?.take(8) ?: return

        if (distinctSwatches.size < 2) {
            visibility = View.INVISIBLE
            return
        }

        val layoutParam = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1.0f)

        for (swatch in distinctSwatches) {
            val color = swatch.rgb
            val view = View(context)
            view.layoutParams = layoutParam
            view.setBackgroundColor(color)
            view.setOnClickListener {
                clickListener?.invoke(color)
            }
            addView(view)
        }
    }
}