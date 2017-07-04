package com.example.ominext.plaidfork.data

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Ominext on 6/16/2017.
 */

class SharedPreManager constructor(context: Context) {
    val preferences: SharedPreferences = context.getSharedPreferences("", Context.MODE_APPEND)

    fun setInt(key: String, value: Int) {
        val edit = preferences.edit()
        edit.putInt(key, value)
        edit.apply()
    }

    fun getInt(key: String, defaultValue: Int = -1): Int {
        return preferences.getInt(key, defaultValue)
    }
}