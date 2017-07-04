package com.example.ominext.plaidfork

import android.app.Application
import android.support.v7.app.AppCompatActivity
import com.example.ominext.plaidfork.di.component.ApplicationComponent
import com.example.ominext.plaidfork.di.component.DaggerApplicationComponent
import com.example.ominext.plaidfork.di.module.ApplicationModule

/**
 * Created by Ominext on 6/12/2017.
 */

class PlaidForkApplication : Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()

        component.inject(this)
    }
}

fun AppCompatActivity.app() = this.application as PlaidForkApplication