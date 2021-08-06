package com.example.practice_mvvm.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class DataStoreManager( val mContext:Context) {

    val Context.dataStore  :DataStore<Preferences> by preferencesDataStore("MVVM")

    companion object{
        val NAME = stringPreferencesKey("ds_name")
        val PWD = stringPreferencesKey("ds_pwd")
    }

    suspend fun saveToDataStore(user:UserDS){
        mContext.dataStore.edit {
            it[NAME]=user.name
            it[PWD]=user.pwd
        }
    }

    suspend fun getFromDataStore()=mContext.dataStore.data.map {
        UserDS(
            it[NAME]?:"",
            it[PWD]?:""
        )
    }
}