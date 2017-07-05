package com.example.ominext.plaidfork.ui.detail.presenter

import android.util.Log
import com.example.ominext.plaidfork.base.BasePresenter
import com.example.ominext.plaidfork.data.DataManager
import com.example.ominext.plaidfork.ui.detail.view.DetailView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Ominext on 6/13/2017.
 */

class DetailPresenter @Inject constructor(dataManager: DataManager, disposable: CompositeDisposable) :
        BasePresenter<DetailView>(dataManager, disposable) {

    var page: Int = 1

    fun loadComments(idShot: Int, isFirst: Boolean = false) {
        if (isFirst) {
            page = 1
        }

        dataManager.getCommentShot(idShot, page).subscribe({ comments ->
            if (comments.isEmpty()) {
                view?.showNoComment()
            } else {
                page++
                view?.showComment(comments)
            }
        }, { error ->
            Log.d("Error", error.message)
        })
    }
}
