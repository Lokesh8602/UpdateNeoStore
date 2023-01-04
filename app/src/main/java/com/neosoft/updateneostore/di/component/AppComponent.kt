package com.neosoft.updateneostore.di.component

import com.neosoft.updateneostore.MainActivity
import com.neosoft.updateneostore.di.Module.NetworkModule
import com.neosoft.updateneostore.di.Module.SharedPreferenceModule
import com.neosoft.updateneostore.ui.activites.register.RegisterActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,SharedPreferenceModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(registerActivity: RegisterActivity)

}