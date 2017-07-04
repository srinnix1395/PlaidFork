package com.example.ominext.plaidfork.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.ominext.plaidfork.app
import com.example.ominext.plaidfork.di.component.ActivityComponent
import com.example.ominext.plaidfork.di.component.DaggerActivityComponent
import com.example.ominext.plaidfork.di.module.ActivityModule

/**
 * Created by Ominext on 6/12/2017.
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {
    lateinit var activityComponent: ActivityComponent
    var currentFragment: BaseFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initDagger()
        initChildView()
    }

    private fun initDagger() {
        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(app().component)
                .activityModule(ActivityModule(this))
                .build()
        injecting()
    }

    protected open fun initChildView() {

    }

    abstract fun getLayoutId(): Int

    open fun injecting() {

    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed()
    }
}