package com.example.practice_mvvm.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.practice_mvvm.R
import com.example.practice_mvvm.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {
    private val TAG = "MainActivity"

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Log.d(TAG, "onCreate: ")
    }
}
