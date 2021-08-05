package com.example.practice_mvvm.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practice_mvvm.R
import com.example.practice_mvvm.databinding.FragmentUsersListBinding
import com.example.practice_mvvm.network.LoadingState
import com.example.practice_mvvm.viewModels.UsersListViewModel
import com.example.practice_mvvm.views.Adapters.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

/**
 * A fragment representing a list of Items.
 */
class FragmentUsersList : Fragment() {
    private val TAG = "Fragment_2"
    lateinit var binding: FragmentUsersListBinding
    private val usersListViewModel by  viewModel<UsersListViewModel>()
    lateinit var userAdapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModelObserver()
    }

    private fun initViews() {
        binding.apply {
            userAdapter= UserListAdapter(requireContext(),ArrayList())
            usersListRecyclerView.layoutManager=LinearLayoutManager(requireContext())
            usersListRecyclerView.hasFixedSize()
        }
    }

    private fun initViewModelObserver() {
        usersListViewModel._loadingState.observe(requireActivity(), Observer {
            when(it.status){
                LoadingState.Status.LOADING->Toast.makeText(requireContext(),"LOADING",Toast.LENGTH_LONG).show()
                LoadingState.Status.SUCCESS->Toast.makeText(requireContext(),"SUCCESS",Toast.LENGTH_LONG).show()
                LoadingState.Status.FAILED->Toast.makeText(requireContext(),it.msg,Toast.LENGTH_LONG).show()
            }
        })
        usersListViewModel.userData.observe(requireActivity(), Observer {
            if (it.data!=null){
                userAdapter= UserListAdapter(requireContext(),it.data)
                binding.usersListRecyclerView.adapter=userAdapter
            }else{
                Log.d(TAG, "initViewModelObserver: NULL")
            }
        })

    }

}