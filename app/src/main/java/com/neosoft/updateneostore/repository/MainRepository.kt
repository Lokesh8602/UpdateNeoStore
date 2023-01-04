package com.neosoft.updateneostore.repository

import com.neosoft.updateneostore.api.RetrofitService
import javax.inject.Inject


class MainRepository @Inject constructor(private val retrofitService: RetrofitService?) {

     suspend fun register(first_name: String,last_name: String, email: String,password: String,
                        confirm_password: String,gender:String, phone_no: Long) = retrofitService?.register(first_name,last_name,email,password,confirm_password,gender,phone_no)

     suspend fun login(email: String,password:String) = retrofitService?.loginUser(email,password)


    fun productList(productCategoryId: String) = retrofitService?.productList(productCategoryId)

}