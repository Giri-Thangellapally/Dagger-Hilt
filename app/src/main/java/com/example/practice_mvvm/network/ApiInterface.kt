package com.example.practice_mvvm.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.practice_mvvm.models.login.Data
import com.example.practice_mvvm.models.login.LoginResponseModel
import com.example.practice_mvvm.models.login.UserListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

/**
 *@Author By Giri Thangellapally on 23-07-2021.
 */
interface ApiInterface {

    //login
    @FormUrlEncoded
    @POST("/api/login")
     fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Deferred<LoginResponseModel>

    @GET("api/users")
    fun getUsersList(@Query("page") pageNmbr: Int):Deferred<UserListResponse>

/*  @GET("/api/users")
  suspend  fun getUsersList(@Query("page") page: Int): Response<UsersListResponseModel>*/

}