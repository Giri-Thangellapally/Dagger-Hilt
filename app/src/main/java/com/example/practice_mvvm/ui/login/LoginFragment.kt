package com.example.practice_mvvm.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.practice_mvvm.R
import com.example.practice_mvvm.Utils.Util.Companion.showToast
import com.example.practice_mvvm.databinding.FragmentLoginBinding
import com.example.practice_mvvm.network.LoadingState
import com.example.practice_mvvm.viewModels.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private  val TAG = "Fragment_1"
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

                loginViewModel.loginResponseModel.observe(requireActivity(), Observer {
                    if (it.token.isNullOrEmpty()) {
                        showToast(requireContext(), "Token Null !!")
                    } else {
                        showToast(requireContext(), it.token)
                        val navController = findNavController()
                        navController.navigate(R.id.action_loginFragment_to_fragmentUsersList)
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