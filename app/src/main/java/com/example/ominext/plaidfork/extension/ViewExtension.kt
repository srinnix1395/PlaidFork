package com.example.ominext.plaidfork.extension

import android.support.v4.app.Fragment
import com.example.ominext.plaidfork.R
import com.example.ominext.plaidfork.base.BaseActivity
import com.example.ominext.plaidfork.base.BaseFragment

/**
 * Created by Ominext on 7/4/2017.
 */

val BaseFragment.parentActivity : BaseActivity
    get() = activity as BaseActivity

fun BaseActivity.addFragment(fragment: Fragment, addToBackStack: Boolean = true) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(R.id.layoutContent, fragment)
    if (addToBackStack) {
        transaction.addToBackStack(null)
    }
    transaction.commit()
}

