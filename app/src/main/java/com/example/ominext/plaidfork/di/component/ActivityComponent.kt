package com.example.ominext.plaidfork.di.component

import com.example.ominext.plaidfork.di.PerActivity
import com.example.ominext.plaidfork.di.module.ActivityModule
import com.example.ominext.plaidfork.ui.detail.DetailFragment
import com.example.ominext.plaidfork.MainActivity
import com.example.ominext.plaidfork.ui.home.HomeFragment
import dagger.Component

/**
 * Created by Ominext on 6/12/2017.
 */

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(detailActivity: DetailFragment)

    fun inject(homeFragment: HomeFragment)

}