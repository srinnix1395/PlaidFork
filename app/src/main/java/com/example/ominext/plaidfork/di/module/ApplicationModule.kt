package com.example.ominext.plaidfork.di.module

import android.content.Context
import com.example.ominext.plaidfork.PlaidForkApplication
import com.example.ominext.plaidfork.data.ApiService
import com.example.ominext.plaidfork.data.BASE_URL
import com.example.ominext.plaidfork.data.DataManager
import com.example.ominext.plaidfork.data.SharedPreManager
import com.example.ominext.plaidfork.di.ApplicationContext
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Ominext on 6/12/2017.
 */
@Module
class ApplicationModule(var application: PlaidForkApplication) {

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @ApplicationContext
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesManager(@ApplicationContext context: Context): SharedPreManager {
        return SharedPreManager(context)
    }

    @Provides
    fun provideDataManager(apiService: ApiService): DataManager {
        return DataManager(apiService)
    }
}