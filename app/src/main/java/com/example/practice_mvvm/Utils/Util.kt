package com.example.practice_mvvm.Utils

import android.content.Context
import android.widget.Toast

/**
 *@Author By Giri Thangellapally on 26-07-2021.
 */

class Util {

    companion object{
        fun showToast(context: Context?,message:String){
            Toast.makeText(context,message,Toast.LENGTH_LONG).show()
        }
    }
}