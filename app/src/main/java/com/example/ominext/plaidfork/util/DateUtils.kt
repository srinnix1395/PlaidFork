package com.example.ominext.plaidfork.util

import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Admin on 14-Jun-17.
 */

object DateUtils {

    val DIFFERENT_SINGLE_HOUR = "%d hour ago"
    val DIFFERENT_MORE_HOURS = "%d hours ago"
    val DIFFERENT_SINGLE_MINUTE = "%d minute ago"
    val DIFFERENT_MORE_MINUTES = "%d minutes ago"

    fun parse(date: Date?): String? {
        if (date == null) return null
        val elapsedMillis = Date().time - date.time
        val quantity: Int
        if ((elapsedMillis / TimeUnit.DAYS.toMillis(1)).toInt() > 0) {
            return DateFormat.getDateInstance().format(date)
        } else if ((elapsedMillis / TimeUnit.HOURS.toMillis(1)).toInt() > 0) {
            quantity = (elapsedMillis / TimeUnit.HOURS.toMillis(1)).toInt()
            return if (quantity <= 1) String.format(DIFFERENT_SINGLE_HOUR, quantity) else String.format(DIFFERENT_MORE_HOURS, quantity)
        } else {
            quantity = (elapsedMillis / TimeUnit.MINUTES.toMillis(1)).toInt()
            return if (quantity <= 1) String.format(DIFFERENT_SINGLE_MINUTE, quantity) else String.format(DIFFERENT_MORE_MINUTES, quantity)
        }
    }
}
