package com.example.practice_mvvm.views.Adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun ImageAdapter (  imageView: ImageView,   imageUrl:String){

    imageView.load(imageUrl)
}
