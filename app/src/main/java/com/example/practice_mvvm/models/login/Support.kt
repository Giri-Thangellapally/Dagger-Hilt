package com.example.practice_mvvm.models.login


import com.google.gson.annotations.SerializedName

data class Support(
    @SerializedName("text")
    val text: String = "",
    @SerializedName("url")
    val url: String = ""
)