package com.example.ominext.plaidfork.base

import android.content.Context

/**
 * Created by Ominext on 6/12/2017.
 */
interface BaseView {
    fun showLoading()

    fun hideLoading()

    fun getContext() : Context
}