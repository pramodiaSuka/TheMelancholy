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

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding:FragmentLoginBinding
    private var currentUser:User = User()
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
        binding.btnSignUp.setOnClickListener {
            val action = LoginFragmentDirections.actionRegisterFragment()
            Navigation.findNavController(it).navigate(action)
        }

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.btnLogin.setOnClickListener {
            var reg_username = binding.txtUsername.text.toString()
            var reg_password = binding.txtPassword.text.toString()
            viewModel.login(reg_username, reg_password)
        }

        observeLoginViewModel()

    }

    fun observeLoginViewModel(){
        viewModel.currentUser.observe(viewLifecycleOwner, Observer {
            currentUser = it
        })
        viewModel.logStatusLD.observe(viewLifecycleOwner, Observer {
            if (it == true){
//                Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                val action = LoginFragmentDirections.actionItemHome(currentUser.username.toString())
                Navigation.findNavController(requireView()).navigate(action)
            } else {
                Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

}