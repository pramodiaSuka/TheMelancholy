package com.maverick.themelancholy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.maverick.themelancholy.R
import com.maverick.themelancholy.databinding.FragmentHomeBinding
import com.maverick.themelancholy.viewmodel.ListNewsViewModel

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewModel:ListNewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListNewsViewModel::class.java)
        viewModel.refresh()

        //observeListNewsViewModel()
    }

    fun observeListNewsViewModel(){
        viewModel.newsListLD.observe(viewLifecycleOwner, Observer {
            //motorcycleListAdapter.updateMotorcycleList(it)
        })
    }
}