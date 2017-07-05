package com.example.ominext.plaidfork.ui.detail.view

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import com.example.ominext.plaidfork.R
import com.example.ominext.plaidfork.base.BaseFragment
import com.example.ominext.plaidfork.data.model.Comment
import com.example.ominext.plaidfork.data.model.Shot
import com.example.ominext.plaidfork.extension.load
import com.example.ominext.plaidfork.extension.loadAnimated
import com.example.ominext.plaidfork.extension.parentActivity
import com.example.ominext.plaidfork.extension.setTextWithFormatNumber
import com.example.ominext.plaidfork.ui.detail.adapter.CommentAdapter
import com.example.ominext.plaidfork.ui.detail.presenter.DetailPresenter
import com.example.ominext.plaidfork.util.DateUtils
import com.example.ominext.plaidfork.util.LinkUtils
import com.example.ominext.plaidfork.widget.EndlessScrollListener
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : BaseFragment(), DetailView {

    companion object {
        const val SHOT_DATA = "shot_data"
        const val TRANSITION_NAME: String = "transition_name"
    }

    @Inject
    lateinit var detailPresenter: DetailPresenter

    private var shot: Shot? = null

    private var commentAdapter: CommentAdapter? = null

    override fun getContext(): Context = parentActivity

    override fun getLayoutId(): Int = R.layout.fragment_detail

    override fun injecting() {
        parentActivity.activityComponent.inject(this)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getData(arguments: Bundle) {
        super.getData(arguments)
        shot = arguments.getParcelable(SHOT_DATA)
        ivCover.transitionName = arguments.getString(TRANSITION_NAME)
    }

    override fun initChildView() {
        toolbarDetail.setNavigationIcon(R.drawable.ic_back)
        toolbarDetail.setNavigationOnClickListener {
            onBackPressed()
        }

        collapsingToolbarLayout?.apply {
            setCollapsedTitleTextColor(Color.TRANSPARENT)
            setExpandedTitleColor(Color.TRANSPARENT)
        }

        nestedScrollView?.apply {
            clipToPadding = false
        }

        rvComments.apply {
            commentAdapter = CommentAdapter(arrayListOf())
            layoutManager = LinearLayoutManager(parentActivity)
            val loadListener = object : EndlessScrollListener(layoutManager) {
                override fun onLoadMore() {
                    detailPresenter.loadComments(shot?.id!!)
                }
            }
            addOnScrollListener(loadListener)
            adapter = commentAdapter
        }

        showShot()
    }

    override fun showNoComment() {
        setLoadMoreFinished()
    }

    override fun showComment(comments: ArrayList<Comment>) {
        commentAdapter?.comments?.addAll(comments)
        commentAdapter?.notifyDataSetChanged()
        setLoadMoreFinished()
    }

    override fun showShot() {
        if (shot?.animate ?: false) {
            ivCover.loadAnimated(shot?.images?.hidpi,
                    asGif = true,
                    generatePalette = true,
                    paletteListener = {
                        paletteView.setSwatches(it.swatches)
                    })
        } else {
            ivCover.load(shot?.images?.hidpi,
                    generatePalette = true,
                    paletteListener = {
                        paletteView.setSwatches(it.swatches)
                    })
        }

        tvTitle.text = shot?.title ?: ""

        tvAuthorName.text = shot?.user?.name ?: ""
        ivAuthorImage.load(shot?.user?.avatar, rounded = true)
        tvDate.text = DateUtils.parse(shot?.createdTime) ?: ""

        tvLikeCount.setTextWithFormatNumber(R.plurals.like_number, shot?.likeCount)
        tvViewCount.setTextWithFormatNumber(R.plurals.view_number, shot?.viewCount)
        tvResponseCount.setTextWithFormatNumber(R.plurals.comment_number, shot?.commentCount)

        shot?.description?.let {
            LinkUtils.setTextWithLinks(tvDescription, shot?.description!!)
        }

        detailPresenter.loadComments(shot?.id!!, isFirst = true)
    }

    override fun onResume() {
        super.onResume()
        detailPresenter.addView(this)
    }

    override fun onPause() {
        super.onPause()
        detailPresenter.detachView()
    }

    fun setLoadMoreFinished() {
        val size = commentAdapter?.comments?.size
        if (size != null && size > 0) {
            commentAdapter?.comments?.removeAt(size - 1)
            commentAdapter?.notifyItemRemoved(size - 1)
        }
    }
}


