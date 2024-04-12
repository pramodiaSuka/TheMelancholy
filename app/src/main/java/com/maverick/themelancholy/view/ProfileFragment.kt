package com.maverick.themelancholy.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.maverick.themelancholy.R
import com.maverick.themelancholy.databinding.FragmentProfileBinding
import com.maverick.themelancholy.model.User
import com.maverick.themelancholy.viewmodel.ProfileViewModel
import com.maverick.themelancholy.viewmodel.SharedViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date

class ProfileFragment : Fragment() {
    private lateinit var binding:FragmentProfileBinding
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var viewModel: ProfileViewModel
    //private lateinit var
    var currentUser:User = User()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel.currentUsernameLD.observe(viewLifecycleOwner, Observer {
            viewModel.getUser(it)
        })

        binding.btnLogout.setOnClickListener {
            sharedViewModel.setCurrentUsername("")
            val action = ProfileFragmentDirections.actionLoginFragmentFromProfile()
            Navigation.findNavController(requireView()).navigate(action)
        }


        binding.btnUpdateProfile.setOnClickListener {
            var newFirstName = currentUser.first_name
            var newLastName = currentUser.last_name
            var newPassword = "NoUpdate"
            var currentPassword = binding.txtCurrentPass.text.toString()
            if (binding.txtChangeFirstName.text.toString() != ""){
                newFirstName = binding.txtChangeFirstName.text.toString()
            }
            if (binding.txtChangeLastName.text.toString() != ""){
                newLastName = binding.txtChangeLastName.text.toString()
            }
            if (currentPassword != ""){
                if (binding.txtNewPass.text.toString() != ""){
                    if (binding.txtRePass.text.toString() == binding.txtNewPass.text.toString()){
                        newPassword = binding.txtNewPass.text.toString()
                        viewModel.update(newFirstName!!, newLastName!!, newPassword, currentPassword, currentUser.username.toString())
                    }
                }
                else {
                    viewModel.update(newFirstName!!, newLastName!!, newPassword, currentPassword, currentUser.username.toString())
                }
                observeProfileViewModel()
                binding.txtProfileDisplayName.text = "${newFirstName} ${newLastName}"
                binding.txtChangeFirstName.setText("")
                binding.txtChangeLastName.setText("")
                binding.txtNewPass.setText("")
                binding.txtRePass.setText("")
                binding.txtCurrentPass.setText("")
            }
            else {
                Toast.makeText(requireContext(), "Please write current password!", Toast.LENGTH_SHORT).show()
            }
        }

        observeProfileViewModel()
    }

    fun observeProfileViewModel(){
        viewModel.currentUser.observe(viewLifecycleOwner, Observer {
            binding.txtProfileDisplayName.text = "${it.first_name} ${it.last_name}"
            binding.txtProfileUsername.text = "${it.username}"
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val date: Date = inputFormat.parse(it.created_at)
            val outputFormat = SimpleDateFormat("dd MMMM yyyy")
            val formattedDate: String = outputFormat.format(date)
            binding.txtProfileDateJoined.text = "Date Joined: $formattedDate"

            val picasso = Picasso.Builder(requireContext())
            picasso.listener { picasso, uri, exception ->
                exception.printStackTrace()
            }
            picasso.build().load(it.image_url).into(binding.imgProfileUser, object:
                    Callback {
                    override fun onSuccess() {
                        binding.imgProfileUser.visibility = View.VISIBLE
                    }

                    override fun onError(e: Exception?) {
                    Log.e("picasso error", e.toString())
                }
            })

            currentUser = it
        })

        viewModel.updateStatusLD.observe(viewLifecycleOwner, Observer {
            if (it == true){
                Toast.makeText(requireContext(), "Account Updated Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Update Failed!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}