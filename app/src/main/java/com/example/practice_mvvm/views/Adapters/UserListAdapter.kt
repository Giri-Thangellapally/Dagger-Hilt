package com.example.practice_mvvm.views.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.practice_mvvm.R
import com.example.practice_mvvm.databinding.ItemUserViewBinding
import com.example.practice_mvvm.models.login.Data

class UserListAdapter(private val mContext:Context, private val users:List<Data>) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    lateinit var binding : ItemUserViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view= LayoutInflater.from(mContext).inflate(R.layout.item_user_view,parent,false)
        binding= DataBindingUtil.bind(view)!!
        return UserViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

   inner class UserViewHolder(binding: ItemUserViewBinding) :RecyclerView.ViewHolder(binding.root){
       fun bind(user : Data){
           binding.userData=user
           binding.executePendingBindings()
       }
    }



}