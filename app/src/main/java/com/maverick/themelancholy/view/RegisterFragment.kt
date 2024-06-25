package com.maverick.themelancholy.view

import android.icu.util.Calendar
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
import com.maverick.themelancholy.databinding.FragmentRegisterBinding
import com.maverick.themelancholy.model.User
import com.maverick.themelancholy.viewmodel.RegisterViewModel
import java.text.SimpleDateFormat

class RegisterFragment : Fragment(), UserClickListener, UserRegisterClickListener {
    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding:FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.user = User()
        binding.registerListener = this
        binding.loginListener = this
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        observeRegisterViewModel()

//        binding.btnRegister.setOnClickListener {
//
//            var reg_username = binding.txtUsernameReg.text.toString()
//            var reg_email = binding.txtEmailReg.text.toString()
//            var reg_first_name = binding.txtFirstNameReg.text.toString()
//            var reg_last_name = binding.txtLastNameReg.text.toString()
//            var reg_image_url = binding.txtImageUrlReg.text.toString()
//            var reg_password = binding.txtPasswordReg.text.toString()
//            var reg_repassword = binding.txtRePassword.text.toString()
//
//            if (reg_password == reg_repassword){
//                viewModel.register(reg_username, reg_email, reg_password, reg_first_name, reg_last_name, reg_image_url)
//            }
//            else {
//                Toast.makeText(requireContext(), "Please retype the same password!", Toast.LENGTH_SHORT).show()
//            }
//        }

//        binding.btnSignIn.setOnClickListener {
//            val action = RegisterFragmentDirections.actionLoginFragment()
//            Navigation.findNavController(it).navigate(action)
//        }


    }

    fun observeRegisterViewModel(){
        viewModel.regStatusLD.observe(viewLifecycleOwner, Observer {
            if (it == true){
                Toast.makeText(requireContext(), "Account Created Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Register Failed!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onUserClick(v: View) {
        val action = RegisterFragmentDirections.actionLoginFragment()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onUserRegisterClick(v: View) {
        var reg_username = binding.user!!.username
        var reg_email = binding.user!!.email!!
        var reg_first_name = binding.user!!.first_name!!
        var reg_last_name = binding.user!!.last_name!!
        var reg_image_url = binding.user!!.image_url!!
        var reg_password = binding.user!!.password
        var reg_repassword = binding.txtRePassword.text.toString()

        if (reg_password == reg_repassword){
            viewModel.register(reg_username, reg_email, reg_password, reg_first_name, reg_last_name, reg_image_url)
        }
        else {
            Toast.makeText(requireContext(), "Please retype the same password!", Toast.LENGTH_SHORT).show()
        }
    }


}