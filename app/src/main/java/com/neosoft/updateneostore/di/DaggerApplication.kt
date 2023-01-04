package com.neosoft.updateneostore.di

import android.app.Application
import com.neosoft.updateneostore.di.Module.SharedPreferenceModule
import com.neosoft.updateneostore.di.component.AppComponent
import com.neosoft.updateneostore.di.component.DaggerAppComponent

class DaggerApplication:Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().sharedPreferenceModule(SharedPreferenceModule(this))
            .build()    }
}