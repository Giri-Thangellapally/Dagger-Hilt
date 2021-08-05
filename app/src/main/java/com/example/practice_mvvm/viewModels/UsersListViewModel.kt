package com.example.practice_mvvm.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_mvvm.Repositories.UserListRepository
import com.example.practice_mvvm.models.login.Data
import com.example.practice_mvvm.models.login.UserListResponse
import com.example.practice_mvvm.network.LoadingState
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class UsersListViewModel (private val userListRepository: UserListRepository) :ViewModel(){

       var userData = MutableLiveData<UserListResponse>()
      var _loadingState = MutableLiveData<LoadingState>()

    init {
        userData=userListRepository.usersData
        fethData()
    }

    private fun fethData() {
        viewModelScope.launch {
            try {
                 _loadingState.value = LoadingState.loading
                 userListRepository.getAllUsers(2)
                Log.d("fethData:",userData.value.toString())
                _loadingState.value = LoadingState.loaded
            }catch (exception : Exception){
                _loadingState.value = LoadingState.fail(exception.message)
                Log.d("fethData:",exception.message.toString())
            }
        }
    }

}