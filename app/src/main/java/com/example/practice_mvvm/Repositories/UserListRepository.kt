package com.example.practice_mvvm.Repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.practice_mvvm.models.login.Data
import com.example.practice_mvvm.models.login.UserListResponse
import com.example.practice_mvvm.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.log

class UserListRepository(private var apiInterface: ApiInterface) {
      var usersData=MutableLiveData<UserListResponse>()
    suspend fun getAllUsers(pageNmbr: Int) : MutableLiveData<UserListResponse>  {
        withContext(Dispatchers.IO){
            usersData.postValue(apiInterface.getUsersList(pageNmbr).await())
        }
        return usersData
    }
}