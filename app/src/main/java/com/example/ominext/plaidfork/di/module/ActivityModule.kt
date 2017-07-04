package com.example.ominext.plaidfork.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.example.ominext.plaidfork.base.BasePresenter
import com.example.ominext.plaidfork.di.ActivityContext
import com.example.ominext.plaidfork.ui.home.HomePresenter
import com.example.ominext.plaidfork.ui.home.HomeView
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ominext on 6/12/2017.
 */

@Module
class ActivityModule(var activity: AppCompatActivity){

    @Provides
    @ActivityContext
    fun provideActivityContext() :Context?{
        return activity
    }

    @Provides
    fun provideHomePresenter(presenter: HomePresenter): BasePresenter<HomeView> {
        return presenter
    }

    @Provides
    fun provideDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}