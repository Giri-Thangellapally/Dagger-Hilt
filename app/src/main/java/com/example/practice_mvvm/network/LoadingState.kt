package com.example.practice_mvvm.network

data class LoadingState private constructor(val status:Status,val msg:String?=null){

companion object {
    val loaded = LoadingState(Status.SUCCESS)
    val loading = LoadingState(Status.LOADING)
    fun fail(msg:String?) = LoadingState(Status.FAILED,msg)
}

    enum class Status{
        LOADING,
        SUCCESS,
        FAILED
    }
}

