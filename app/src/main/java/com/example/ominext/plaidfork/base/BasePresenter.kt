package com.example.ominext.plaidfork.base

import com.example.ominext.plaidfork.data.DataManager
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ominext on 6/12/2017.
 */
open class BasePresenter<T : BaseView>(val dataManager: DataManager, val disposable: CompositeDisposable?) {
    var view: T? = null

    open fun addView(view: T) {
        this.view = view
    }

    open fun detachView() {
        view = null
        disposable?.clear()
    }
}