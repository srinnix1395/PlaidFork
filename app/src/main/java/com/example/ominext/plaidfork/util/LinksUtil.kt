package com.example.ominext.plaidfork.util

import android.os.Build
import android.text.Html
import android.text.Spannable
import android.text.TextUtils
import android.text.style.ClickableSpan
import android.view.MotionEvent
import android.widget.TextView

/**
 * Created by Admin on 15-Jun-17.
 */
object LinkUtils {

    /**
     * https://github.com/hidroh/materialistic/blob/fbcf46ab05b93762387340a8ed22db82b8fa08c5/app/src/main/java/io/github/hidroh/materialistic/AppUtils.java
     */
    fun setTextWithLinks(textView: TextView, text: String) {
        textView.text = fromHtml(text, false)
        textView.setOnTouchListener { v, event ->
            val action = event.action
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
                var x = event.x.toInt()
                var y = event.y.toInt()

                val widget = v as TextView
                x -= widget.totalPaddingLeft
                y -= widget.totalPaddingTop

                x += widget.scrollX
                y += widget.scrollY

                val layout = widget.layout
                val line = layout.getLineForVertical(y)
                val off = layout.getOffsetForHorizontal(line, x.toFloat())

                val link = Spannable.Factory.getInstance()
                        .newSpannable(widget.text)
                        .getSpans(off, off, ClickableSpan::class.java)
                if (link.size != 0) {
                    if (action == MotionEvent.ACTION_UP) {
                        link[0].onClick(widget)
                    }
                    return@setOnTouchListener true
                }
            }
            false
        }
    }

    private fun fromHtml(htmlText: String, compact: Boolean): CharSequence? {
        if (TextUtils.isEmpty(htmlText)) {
            return null
        }
        val spanned: CharSequence
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            spanned = Html.fromHtml(htmlText, if (compact)
                Html.FROM_HTML_MODE_COMPACT
            else
                Html.FROM_HTML_MODE_LEGACY)
        } else {

            spanned = Html.fromHtml(htmlText)
        }
        return trim(spanned)
    }

    private fun trim(s: CharSequence): CharSequence {
        var start = 0
        var end = s.length
        while (start < end && Character.isWhitespace(s[start])) {
            start++
        }
        while (end > start && Character.isWhitespace(s[end - 1])) {
            end--
        }
        return s.subSequence(start, end)
    }

}