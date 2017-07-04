package com.example.ominext.plaidfork

import android.content.Context
import com.example.ominext.plaidfork.base.BaseActivity
import com.example.ominext.plaidfork.ui.home.HomeFragment


/**
 * Created by Ominext on 6/12/2017.
 */
class MainActivity : BaseActivity() {
    override fun getContext(): Context = this

    override fun injecting() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initChildView() {
        super.initChildView()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.layoutContent, HomeFragment())
        transaction.commit()
    }

}