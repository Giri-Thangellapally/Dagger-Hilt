package com.example.practice_mvvm.models.login


import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("token")
    val token: String
)