package com.example.ominext.plaidfork.ui.home.view

import com.example.ominext.plaidfork.base.BaseView
import com.example.ominext.plaidfork.data.model.Shot

/**
 * Created by Ominext on 6/13/2017.
 */
interface HomeView : BaseView {
    fun loadSuccessfully(page: Int, t: ArrayList<Shot>)

    fun loadFail(page: Int)

    fun setRefreshing(refreshing: Boolean)

    fun addLoadMoreItem()
}