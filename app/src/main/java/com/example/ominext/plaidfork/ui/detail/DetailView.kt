package com.example.ominext.plaidfork.ui.detail

import com.example.ominext.plaidfork.base.BaseView
import com.example.ominext.plaidfork.data.model.Comment

/**
 * Created by Ominext on 6/13/2017.
 */
interface DetailView : BaseView {
    fun showShot()

    fun showComment(comments: ArrayList<Comment>)

    fun showNoComment()

}
