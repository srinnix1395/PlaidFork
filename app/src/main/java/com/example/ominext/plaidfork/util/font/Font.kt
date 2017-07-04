package com.example.ominext.plaidfork.util.font

import android.content.Context
import android.graphics.Typeface

/**
 * Created by DoanNH on 13-Jun-17.
 */

object Font {
    val FIRASANS = "FiraSans-Regular.ttf"
    val FIRASANS_BOLD = "FiraSans-Bold.ttf"
    val FIRASANS_ITALIC = "FiraSans-Italic.ttf"
    val FIRASANS_BOLD_ITALIC = "FiraSans-BoldItalic.ttf"
}

object FontCache {

    val fontCache = HashMap<String, Typeface>()

    fun getTypeFace(context: Context, fontName: String) : Typeface {
        val typeFace: Typeface? = fontCache[fontName]

        typeFace?.let {
            return it
        }

        fontCache[fontName] = Typeface.createFromAsset(context.assets, fontName)
        return fontCache[fontName]!!
    }
}


