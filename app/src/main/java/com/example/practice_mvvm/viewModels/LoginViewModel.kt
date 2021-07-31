package com.example.practice_mvvm.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.practice_mvvm.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

/**
 *@Author By Giri Thangellapally on 23-07-2021.
 */
class LoginViewModel : ViewModel() {

    fun getUserLoginResponse(email: String, password: String)= liveData(Dispatchers.IO) {
            val apiInterface = RetrofitInstance.getInstance()
        try {
            emit(apiInterface.login(email, password))
        }catch (exception:Exception){
            Log.v("Exc",exception.message.toString())
        }
        }

}

