package com.example.ominext.plaidfork.extension

import android.widget.TextView
import java.text.NumberFormat

/**
 * Created by Admin on 14-Jun-17.
 */

fun TextView.setTextWithFormatNumber(stringRes: Int, number: Int?) {
    val numberFormat = NumberFormat.getNumberInstance().format(number ?: 0)
    text = resources.getQuantityString(stringRes, number ?: 0, numberFormat)
}