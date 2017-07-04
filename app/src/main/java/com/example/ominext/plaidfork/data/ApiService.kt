package com.example.ominext.plaidfork.data

import com.example.ominext.plaidfork.data.model.Comment
import com.example.ominext.plaidfork.data.model.Shot
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Ominext on 6/12/2017.
 */

interface ApiService {
    @GET("shots")
    fun getListShot(@Header(AUTH_HEADER) header: String,
                    @Query("page") page: Int): Observable<ArrayList<Shot>>


    @GET("shots/{id_shot}/comments")
    fun getCommentShot(@Header(AUTH_HEADER) header: String, @Path("id_shot") idShot: Int,
                       @Query("page") page: Int, @Query("per_page") pageSize: Int): Single<ArrayList<Comment>>

}

