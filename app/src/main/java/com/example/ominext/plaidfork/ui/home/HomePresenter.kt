package com.example.ominext.plaidfork.ui.home

import android.content.Context
import android.util.Log
import com.example.ominext.plaidfork.base.BasePresenter
import com.example.ominext.plaidfork.data.DataManager
import com.example.ominext.plaidfork.data.SharedPreManager
import com.example.ominext.plaidfork.data.model.Shot
import com.example.ominext.plaidfork.ui.detail.DetailActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Ominext on 6/12/2017.
 */

class HomePresenter @Inject constructor(dataManager: DataManager, disposable: CompositeDisposable) :
        BasePresenter<HomeView>(dataManager, disposable) {

    private var page = 1
    private var subject = PublishSubject.create<Any>()

    @Inject
    lateinit var preferences: SharedPreManager

    init {
        disposable.add(subject.debounce(1L, TimeUnit.SECONDS)
                .flatMap {
                    return@flatMap dataManager.getListShot(page)
                }
                .doAfterNext({
                    view?.setRefreshing(false)
                })
                .subscribe({ t ->
                    view?.loadSuccessfully(page, t)
                    increasePage()
                }, { t ->
                    view?.loadFail(page)
                    Log.e("he", t?.message ?: "null")
                }))


    }

    fun onClickShot(context: Context, list: ArrayList<Any>, position: Int) {
        val shot = list[position]
        if (view != null) {
            DetailActivity.navigate(context, shot as Shot)
        }
    }

    fun getShots() {
        if (page > 1) {
            view?.addLoadMoreItem()
        } else {
            view?.setRefreshing(true)
        }

        subject.onNext(1)
    }

    private fun increasePage() {
        page++
    }

    fun resetPage() {
        page = 1
    }

}