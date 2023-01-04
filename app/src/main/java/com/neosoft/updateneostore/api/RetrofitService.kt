package com.neosoft.updateneostore.api

import com.neosoft.updateneostore.data.pojo.ProductList
import com.neosoft.updateneostore.data.pojo.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {
    @FormUrlEncoded
    @POST("users/register")
    suspend fun register(
    @Field("first_name") first_name : String,
    @Field("last_name") last_name:String,
    @Field("email") email : String,
    @Field("password") password : String,
    @Field("confirm_password") confirm_password:String,
    @Field("gender") gender:String,
    @Field("phone_no") phone_no: Number): Response<RegisterResponse>


    @FormUrlEncoded
    @POST("users/login")
   suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password:String
    ): Response<RegisterResponse>

    @GET("products/getList?")
    fun productList(
        @Query("product_category_id") product_category_id:String
    ):Call<ProductList>

}