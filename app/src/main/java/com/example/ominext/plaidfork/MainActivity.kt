package com.example.ominext.plaidfork

import android.content.Context
import com.example.ominext.plaidfork.base.BaseActivity
import com.example.ominext.plaidfork.extension.addFragment
import com.example.ominext.plaidfork.ui.home.view.HomeFragment


/**
 * Created by Ominext on 6/12/2017.
 */
class MainActivity : BaseActivity() {
    override fun getContext(): Context = this

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initChildView() {
        addFragment(HomeFragment())
    }
}