package com.example.ominext.plaidfork.ui.home.presenter

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.widget.ImageView
import com.example.ominext.plaidfork.R
import com.example.ominext.plaidfork.base.BaseActivity
import com.example.ominext.plaidfork.base.BasePresenter
import com.example.ominext.plaidfork.data.DataManager
import com.example.ominext.plaidfork.data.SharedPreManager
import com.example.ominext.plaidfork.data.model.Shot
import com.example.ominext.plaidfork.ui.detail.view.DetailFragment
import com.example.ominext.plaidfork.ui.home.view.HomeFragment
import com.example.ominext.plaidfork.ui.home.view.HomeView
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
                    Log.e("he", t.message ?: "null")
                }))


    }

    fun onClickShot(fragmentOne: HomeFragment, activity: BaseActivity, shot: Shot, position: Int, imageView: ImageView) {
        val bundle = Bundle()
        bundle.putString(DetailFragment.TRANSITION_NAME, "image_$position")
        bundle.putParcelable(DetailFragment.SHOT_DATA, shot)

        val fragmentTwo = DetailFragment()
        fragmentTwo.arguments = bundle

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Inflate transitions to apply
            val changeTransform = TransitionInflater.from(activity).inflateTransition(R
                    .transition.detail_fragment_transition)
            val explodeTransform = TransitionInflater.from(activity).inflateTransition(android.R
                    .transition.fade)

            fragmentOne.sharedElementReturnTransition = changeTransform
            fragmentOne.exitTransition = explodeTransform

            fragmentTwo.sharedElementEnterTransition = changeTransform
            fragmentTwo.enterTransition = explodeTransform

            // Add second fragmentOne by replacing first
            val ft = activity.supportFragmentManager.beginTransaction()
                    .add(R.id.layoutContent, fragmentTwo)
                    .addToBackStack(null)
                    .addSharedElement(imageView, "image_$position")
            // Apply the transaction
            ft.commit()
        } else {
            // Code to run on older devices
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