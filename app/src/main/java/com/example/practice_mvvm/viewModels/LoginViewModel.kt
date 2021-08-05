package com.example.practice_mvvm.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.practice_mvvm.Repositories.LoginRepository
import com.example.practice_mvvm.models.login.LoginResponseModel
import com.example.practice_mvvm.network.LoadingState
import com.example.practice_mvvm.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 *@Author By Giri Thangellapally on 23-07-2021.
 */
class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

     var loginResponseModel= MutableLiveData<LoginResponseModel>()
     val  _loadingState =MutableLiveData<LoadingState>()

    init {
        loginResponseModel=loginRepository.loginResponseModel
        doLogin("","")
    }

  fun doLogin(email:String,password:String) {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.loading
              loginResponseModel=  loginRepository.getLogin("eve.holt@reqres.in", "cityslicka")
                _loadingState.value = LoadingState.loaded
            }
            catch (error:Exception){
                Log.d("FUCK", error.toString())
                _loadingState.value= LoadingState.fail(error.message.toString())
            }
        }
    }

/*    suspend fun userLogin(email: String,password: String){
      withContext(Dispatchers.IO){

       loginRepository.userLogin(email,password)

}*/

    }



