package com.example.ominext.plaidfork.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Ominext on 6/13/2017.
 */
abstract class BaseFragment : Fragment(), BaseView {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(getLayoutId(), container, false)
    }

    abstract fun getLayoutId(): Int

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injecting()
        initChildView()
    }

    abstract fun injecting()

    abstract fun initChildView()

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}

fun Fragment.activity(): BaseActivity {
    return this.activity as BaseActivity
}