package com.example.practice_mvvm.network

import com.example.practice_mvvm.models.login.LoginResponseModel
import retrofit2.Response
import retrofit2.http.*

/**
 *@Author By Giri Thangellapally on 23-07-2021.
 */
interface ApiInterface {

    //login
    @FormUrlEncoded
    @POST("/api/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponseModel>

/*  @GET("/api/users")
  suspend  fun getUsersList(@Query("page") page: Int): Response<UsersListResponseModel>*/

}