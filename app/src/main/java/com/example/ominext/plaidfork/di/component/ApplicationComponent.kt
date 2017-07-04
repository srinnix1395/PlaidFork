package com.example.ominext.plaidfork.di.component

import com.example.ominext.plaidfork.PlaidForkApplication
import com.example.ominext.plaidfork.data.DataManager
import com.example.ominext.plaidfork.data.SharedPreManager
import com.example.ominext.plaidfork.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Ominext on 6/12/2017.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: PlaidForkApplication)

    fun getSharedPreferencesManager(): SharedPreManager

    fun getDataManager() : DataManager
}