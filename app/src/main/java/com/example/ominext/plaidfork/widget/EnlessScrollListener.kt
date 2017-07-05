package com.example.ominext.plaidfork.widget

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by Ominext on 6/13/2017.
 */


abstract class EndlessScrollListener(val layoutManager: RecyclerView.LayoutManager) : RecyclerView.OnScrollListener() {
    private var isLoading: Boolean = false
    private var previousTotalItemCount = 0
    private val visibleThreshold: Int = 2

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy < 0) return

        val totalItemCount = layoutManager.itemCount
        val lastVisibleItemCount = when (layoutManager) {
            is GridLayoutManager -> layoutManager.findLastVisibleItemPosition()
            is LinearLayoutManager -> layoutManager.findLastVisibleItemPosition()
            else -> return
        }

        //if it's still loading and current total item > previous total item -> disable loading
        if (isLoading && totalItemCount > previousTotalItemCount) {
            isLoading = false
            previousTotalItemCount = totalItemCount
        }

        //if it is not loading and users reach the threshold of loading more -> load more data
        val isReachThreshold = lastVisibleItemCount + visibleThreshold >= totalItemCount

        if (!isLoading && isReachThreshold) {
            onLoadMore()
            isLoading = true
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        onStateChanged(newState)
    }

    fun onStateChanged(newState: Int) {

    }

    abstract fun onLoadMore()
}