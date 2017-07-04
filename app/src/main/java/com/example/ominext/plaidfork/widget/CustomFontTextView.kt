package com.example.ominext.plaidfork.widget

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.example.ominext.plaidfork.util.font.Font
import com.example.ominext.plaidfork.util.font.FontCache

/**
 * Created by DoanNH on 13-Jun-17.
 */

class CustomFontTextView : AppCompatTextView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {
        applyCustomFont(context, attributes)
    }


    constructor(context: Context, attributes: AttributeSet, defaultStyle: Int) : super(context,
            attributes, defaultStyle) {
        applyCustomFont(context, attributes)
    }


    private fun getTypeface(context: Context, style: Int): Typeface {
        return when (style) {
            Typeface.BOLD -> FontCache.getTypeFace(context, Font.FIRASANS_BOLD)
            Typeface.BOLD_ITALIC -> FontCache.getTypeFace(context, Font.FIRASANS_BOLD_ITALIC)
            Typeface.ITALIC -> FontCache.getTypeFace(context, Font.FIRASANS_ITALIC)
            else -> FontCache.getTypeFace(context, Font.FIRASANS)
        }
    }

    fun applyCustomFont(context: Context, attributes: AttributeSet?) {
        attributes?.let {
            typeface = getTypeface(context, attributes.getAttributeIntValue("http://schemas.android.com/apk/res/android",
                    "textStyle",
                    Typeface.NORMAL))
        }
    }
}
