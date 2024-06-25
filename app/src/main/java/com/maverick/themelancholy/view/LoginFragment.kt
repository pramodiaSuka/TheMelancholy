package com.maverick.themelancholy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.maverick.themelancholy.R
import com.maverick.themelancholy.databinding.FragmentLoginBinding
import com.maverick.themelancholy.model.User
import com.maverick.themelancholy.viewmodel.LoginViewModel
import com.maverick.themelancholy.viewmodel.RegisterViewModel
import com.maverick.themelancholy.viewmodel.SharedViewModel

class LoginFragment : Fragment(), UserClickListener, UserRegisterClickListener {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding:FragmentLoginBinding
    private lateinit var sharedViewModel:SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.user = User()
        binding.listener = this
        binding.registerListener = this

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        observeLoginViewModel()

//        binding.btnSignUp.setOnClickListener {
//            val action = LoginFragmentDirections.actionRegisterFragment()
//            Navigation.findNavController(it).navigate(action)
//        }

//        binding.btnLogin.setOnClickListener {
//            var reg_username = binding.txtUsername.text.toString()
//            var reg_password = binding.txtPassword.text.toString()
//            viewModel.login(reg_username, reg_password)
//        }



    }

    fun observeLoginViewModel(){
        viewModel.currentUser.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                sharedViewModel.setCurrentUsername(it.username!!)
                val action = LoginFragmentDirections.actionItemHome()
                Navigation.findNavController(requireView()).navigate(action)
            }
            else if (it == null) {
                Toast.makeText(requireContext(), "Username or Password is wrong!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onUserClick(v: View) {
        var reg_username = binding.user!!.username
        var reg_password = binding.user!!.password!!
        viewModel.login(reg_username, reg_password)
    }

    override fun onUserRegisterClick(v: View) {
        val action = LoginFragmentDirections.actionRegisterFragment()
        Navigation.findNavController(v).navigate(action)
    }

}