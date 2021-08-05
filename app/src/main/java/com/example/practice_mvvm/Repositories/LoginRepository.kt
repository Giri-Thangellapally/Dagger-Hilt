package com.example.practice_mvvm.Repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.practice_mvvm.models.login.LoginResponseModel
import com.example.practice_mvvm.network.ApiInterface
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepository(private val apiInterface: ApiInterface):SafeApiRequest() {


    val loginResponseModel =MutableLiveData<LoginResponseModel>()

    suspend fun getLogin(email:String, passWord:String) :MutableLiveData<LoginResponseModel>{
        withContext(Dispatchers.IO){
            loginResponseModel.postValue(apiInterface.login(email,passWord).await())
        }
        return loginResponseModel
    }


/*
    suspend fun userLogin(email: String,passWord: String): Deferred<LoginResponseModel> {

        return apiInterface.login(email,passWord)

    }
*/

}
