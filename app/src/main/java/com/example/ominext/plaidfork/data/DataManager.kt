package com.example.ominext.plaidfork.data

import com.example.ominext.plaidfork.data.model.Comment
import com.example.ominext.plaidfork.data.model.Shot
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ominext on 6/13/2017.
 */
class DataManager constructor(private var apiService: ApiService) {

    fun getListShot(page: Int): Observable<ArrayList<Shot>> {
        return apiService.getListShot(DRIBBBLE_ACCESS_TOKEN, page)
                .applyScheduler()
    }

    fun getCommentShot(idShot: Int, page: Int): Single<ArrayList<Comment>> {
        return apiService.getCommentShot(DRIBBBLE_ACCESS_TOKEN, idShot, page, 40).applyScheduler()
    }

    private fun <T> Single<T>.applyScheduler(): Single<T> {
        return this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }


    private fun <T> Observable<T>.applyScheduler(): Observable<T> {
        return this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}

inline fun <reified T : Any> Gson.fromJson(json: String): T {
    return this.fromJson<T>(json, object : TypeToken<T>() {}.type)
}