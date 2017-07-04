package com.example.ominext.plaidfork.ui.home

import android.graphics.Color
import android.support.transition.TransitionManager
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.example.ominext.plaidfork.R
import com.example.ominext.plaidfork.base.BaseFragment
import com.example.ominext.plaidfork.data.LOADING_TYPE
import com.example.ominext.plaidfork.data.model.Shot
import com.example.ominext.plaidfork.extension.parentActivity
import com.example.ominext.plaidfork.widget.EndlessScrollListener
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

/**
 * Created by Ominext on 6/13/2017.
 */
class HomeFragment : BaseFragment(), HomeView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: HomePresenter

    lateinit var list: ArrayList<Any>
    lateinit var adapter: ShotAdapter

    override fun injecting() {
        parentActivity.activityComponent.inject(this)
    }

    override fun initChildView() {
        toolbar.title = "PLAID FORK"
        toolbar.setTitleTextColor(Color.WHITE)

        list = ArrayList()

        adapter = ShotAdapter(list, fun(position: Int) {
            presenter.onClickShot(parentActivity, list, position)
        })

        val layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(p0: Int): Int {
                return if (list[p0] !is Shot || p0 == 0) 2 else 1
            }
        }
        rvShot.apply {
            adapter = this@HomeFragment.adapter
            this.layoutManager = layoutManager
            addOnScrollListener(object : EndlessScrollListener(layoutManager) {
                override fun onLoadMore() = presenter.getShots()
            })
        }

        refreshLayout.apply {
            setProgressBackgroundColorSchemeColor(ContextCompat.getColor(context, R.color.colorAccent))
            setColorSchemeColors(ContextCompat.getColor(context, android.R.color.white))
            setOnRefreshListener(this@HomeFragment)
        }
        presenter.getShots()
    }

    override fun onRefresh() {
        TransitionManager.beginDelayedTransition(rvShot)
        layoutNoInternet.visibility = View.GONE
        list.clear()
        adapter.notifyDataSetChanged()
        presenter.resetPage()
        presenter.getShots()
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun loadSuccessfully(page: Int, t: ArrayList<Shot>) {
        val count = t.size

        val sizeList = list.size

        if (count > 0) {
            if (page == 1) {
                list.addAll(t)
                adapter.notifyItemRangeInserted(0, count)
            } else {
                list.addAll(sizeList - 1, t)
                adapter.notifyItemRangeInserted(sizeList - 1, count)
            }

            if (page == 1) {
                rvShot.scrollToPosition(0)
            }
        } else if (!list.isEmpty() && list[sizeList - 1] == LOADING_TYPE) {
            list.removeAt(sizeList - 1)
            adapter.notifyItemRemoved(sizeList - 1)
        }
    }

    override fun loadFail(page: Int) {
        if (page == 1) {
            layoutNoInternet.visibility = View.VISIBLE
        } else {
            Toast.makeText(context, "Đã có lỗi", Toast.LENGTH_SHORT).show()
        }
    }

    override fun setRefreshing(refreshing: Boolean) {
        refreshLayout.isRefreshing = refreshing
    }

    override fun addLoadMoreItem() {
        val size = list.size
        if (size > 0 && list[size - 1] != LOADING_TYPE) {
            list.add(LOADING_TYPE)
            rvShot.post {
                adapter.notifyItemInserted(list.size - 1)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.addView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }
}