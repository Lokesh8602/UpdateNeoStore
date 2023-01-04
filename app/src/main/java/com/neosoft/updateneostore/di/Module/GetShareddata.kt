package com.neosoft.updateneostore.di.Module

import android.content.SharedPreferences
import javax.inject.Inject

class GetSharedData  @Inject constructor(val sharedPreferences: SharedPreferences) {

    fun saveToken(token:String){
        val editor =sharedPreferences.edit()
        editor.putString("Access_token",token)
        editor.apply()
    }
    fun getToken():String?{
        return sharedPreferences.getString("Access_token",null)

    }
}