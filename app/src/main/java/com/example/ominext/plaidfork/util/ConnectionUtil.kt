package com.example.ominext.plaidfork.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Ominext on 6/14/2017.
 */

fun checkInternet(context: Context): Boolean {
    val mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return mConnectivityManager.activeNetworkInfo != null
            && mConnectivityManager.activeNetworkInfo.isAvailable
            && mConnectivityManager.activeNetworkInfo.isConnected
}