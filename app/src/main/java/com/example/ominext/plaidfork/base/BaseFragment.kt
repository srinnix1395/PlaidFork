package com.example.ominext.plaidfork.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ominext.plaidfork.extension.parentActivity

/**
 * Created by Ominext on 6/13/2017.
 */
abstract class BaseFragment : Fragment(), BaseView {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(getLayoutId(), container, false)
    }

    abstract fun getLayoutId(): Int

    open fun getData(arguments: Bundle) {

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        injecting()
        arguments?.let {
            getData(arguments)
        }
        initChildView()
    }

    abstract fun injecting()

    abstract fun initChildView()

    fun onBackPressed(){
        val count = fragmentManager.backStackEntryCount
        if (count > 1) {
            fragmentManager.popBackStack()
        } else {
            activity.finish()
        }
    }

    override fun onStart() {
        super.onStart()
        parentActivity.currentFragment = this
    }
}