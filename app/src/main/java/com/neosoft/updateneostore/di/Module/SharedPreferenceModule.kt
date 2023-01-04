package com.neosoft.updateneostore.di.Module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class SharedPreferenceModule(private val application: Application) {



    @Provides
    @Singleton
    fun providesApplication(): Application = application

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context. getSharedPreferences("SharedPrefrenece", Context.MODE_PRIVATE)
    }
}