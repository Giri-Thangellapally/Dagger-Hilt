package com.example.practice_mvvm.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.practice_mvvm.Repositories.LoginRepository
import com.example.practice_mvvm.dataStore.DataStoreManager
import com.example.practice_mvvm.dataStore.UserDS
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

    var loginResponseModel = MutableLiveData<LoginResponseModel>()
    val _loadingState = MutableLiveData<LoadingState>()

    init {
        loginResponseModel = loginRepository.loginResponseModel
    }

    fun doLogin(email: String, password: String):MutableLiveData<LoginResponseModel> {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.loading
                loginResponseModel = loginRepository.getLogin("eve.holt@reqres.in", "cityslicka")
                _loadingState.value = LoadingState.loaded
            } catch (error: Exception) {
                _loadingState.value = LoadingState.fail(error.message.toString())
            }
        }
        return loginResponseModel
    }

    suspend fun saveToDataStore(userDS: UserDS) {
        withContext(Dispatchers.IO) {
          loginRepository.saveToDataStore(userDS)
        }
    }
}




