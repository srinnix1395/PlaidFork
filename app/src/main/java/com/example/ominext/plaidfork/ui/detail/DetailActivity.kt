package com.example.ominext.plaidfork.ui.detail

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.support.v7.graphics.Palette
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.example.ominext.plaidfork.R
import com.example.ominext.plaidfork.base.BaseActivity
import com.example.ominext.plaidfork.data.model.Comment
import com.example.ominext.plaidfork.data.model.Shot
import com.example.ominext.plaidfork.extension.load
import com.example.ominext.plaidfork.extension.loadAnimated
import com.example.ominext.plaidfork.extension.setTextWithFormatNumber
import com.example.ominext.plaidfork.util.DateUtils
import com.example.ominext.plaidfork.util.LinkUtils
import com.example.ominext.plaidfork.widget.EndlessScrollListener
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailView {

    companion object {
        val SHOT_DATA = "shot_data"

        fun navigate(context: Context, shot: Shot) {
            val intent = Intent(context, DetailActivity::class.java)
            val data = Bundle()
            data.putParcelable(SHOT_DATA, shot)
            intent.putExtras(data)
            context.startActivity(intent)
        }
    }


    @Inject
    lateinit var detailPresenter: DetailPresenter

    private var loadListener: EndlessScrollListener? = null

    private var shot: Shot? = null

    private var commentAdapter: CommentAdapter? = null

    override fun getContext(): Context = this

    override fun getLayoutId(): Int = R.layout.activity_detail

    override fun injecting() {
        activityComponent.inject(this)
    }

    override fun initChildView() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
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
            layoutManager = LinearLayoutManager(this@DetailActivity)
            loadListener = object : EndlessScrollListener(layoutManager) {
                override fun onLoadMore() {
                    detailPresenter.loadComments(shot?.id!!)
                }
            }
            addOnScrollListener(loadListener)
            adapter = commentAdapter
        }

        shot = intent.extras.getParcelable(SHOT_DATA)
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
            ivCover.loadAnimated(shot?.images?.hidpi, asGif = true) { resource: GlideDrawable,
                                                                      _: GlideAnimation<in GlideDrawable>? ->
                var gifBitmap: Bitmap? = null
                if (resource is GifDrawable) {
                    gifBitmap = resource.firstFrame
                } else if (resource is GlideBitmapDrawable) {
                    gifBitmap = resource.bitmap
                }

                paletteView.setSwatches(Palette.from(gifBitmap).maximumColorCount(8).generate().swatches)
            }
        } else {
            ivCover.load(shot?.images?.hidpi) {
                paletteView.setSwatches(Palette.from(it).maximumColorCount(8).generate().swatches)
            }
        }

        tvTitle.text = shot?.title ?: ""

        tvAuthorName.text = shot?.user?.name ?: ""
        ivAuthorImage.load(shot?.user?.avatar, asBitmap = true, rounded = true)
        tvDate.text = DateUtils.parse(shot?.createdTime) ?: ""

        tvLikeCount.setTextWithFormatNumber(R.plurals.like_number, shot?.likeCount)
        tvViewCount.setTextWithFormatNumber(R.plurals.view_number, shot?.viewCount)
        tvResponseCount.setTextWithFormatNumber(R.plurals.comment_number, shot?.commentCount)

        LinkUtils.setTextWithLinks(tvDescription, shot?.description!!)

        detailPresenter.loadComments(shot?.id!!, isFirst = true)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
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


