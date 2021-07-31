package com.example.practice_mvvm.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.practice_mvvm.R
import com.example.practice_mvvm.Utils.Util.Companion.showToast
import com.example.practice_mvvm.databinding.FragmentLoginBinding
import com.example.practice_mvvm.viewModels.LoginViewModel

class LoginFragment : Fragment() {
    private  val TAG = "Fragment_1"

    private lateinit var loginViewModel: LoginViewModel

    lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"onViewCreated")

        loginViewModel = ViewModelProvider(this)
            .get(LoginViewModel::class.java)
        binding.apply {
            login.setOnClickListener {
                loading.visibility=View.VISIBLE
                loginViewModel.getUserLoginResponse(
                    username.text.toString(),
                    password.text.toString()
                ).observe(requireActivity(),
                    Observer {
                        if (it.isSuccessful&&!it.body()!!.token.isNullOrEmpty()) {
                            loading.visibility=View.GONE
                            showToast(context, it.body()!!.token)
                           val navController=Navigation.findNavController(view)
                            navController.navigate(R.id.action_loginFragment_to_fragmentUsersList)
                        } else {
                            loading.visibility=View.GONE
                            showToast(context, it.message().toString())
                        }
                    })
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG,"onAttach")
    }

    override fun getContext(): Context? {
        return super.getContext()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"Oncreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG,"onCreateView")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG,"onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG,"onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG,"onDetach")
    }
}