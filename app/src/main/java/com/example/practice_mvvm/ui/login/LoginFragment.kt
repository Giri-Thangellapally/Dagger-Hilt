package com.example.practice_mvvm.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.practice_mvvm.R
import com.example.practice_mvvm.Utils.Util.Companion.showToast
import com.example.practice_mvvm.dataStore.DataStoreManager
import com.example.practice_mvvm.dataStore.UserDS
import com.example.practice_mvvm.databinding.FragmentLoginBinding
import com.example.practice_mvvm.network.LoadingState
import com.example.practice_mvvm.viewModels.LoginViewModel
import com.example.practice_mvvm.views.SecondActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class LoginFragment : Fragment() {

    private  val TAG = "LoginFragment"
    private val loginViewModel by viewModel<LoginViewModel>()
    lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            login.setOnClickListener {
                loginViewModel._loadingState.observe(requireActivity(), Observer {
                    when (it.status) {
                        LoadingState.Status.LOADING -> showLoad(true)
                        LoadingState.Status.FAILED -> showLoad(false)
                        LoadingState.Status.SUCCESS -> showLoad(false)
                    }
                })

                loginViewModel.doLogin(binding.username.toString(),binding.password.toString()).observe(requireActivity(), Observer {
                    if (it.token.isNullOrEmpty()) {
                        showToast(requireContext(), "Token Null !!")
                    } else {
                        showToast(requireContext(), it.token)
                        GlobalScope.launch {
                            loginViewModel.saveToDataStore(UserDS(binding.username.toString(),binding.password.toString()))
                        }
                            findNavController().navigate(R.id.action_loginFragment_to_fragmentUsersList)
                    }
                })
            }
        }
    }

    private fun showLoad(SorN:Boolean) {
        binding.apply {
            if (SorN)
                loading.visibility=View.VISIBLE
            else
                loading.visibility=View.GONE
        }
    }
}