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
import androidx.recyclerview.widget.LinearLayoutManager
import com.maverick.themelancholy.R
import com.maverick.themelancholy.databinding.FragmentHomeBinding
import com.maverick.themelancholy.viewmodel.ListNewsViewModel
import com.maverick.themelancholy.viewmodel.SharedViewModel

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewModel:ListNewsViewModel
    private lateinit var sharedViewModel:SharedViewModel
    private val newsAdapter = NewsAdapter(arrayListOf(), this)
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
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = newsAdapter

        observeListNewsViewModel()

        binding.refreshLayout.setOnRefreshListener {
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }

    }



    fun observeListNewsViewModel(){
        viewModel.newsListLD.observe(viewLifecycleOwner, Observer {
            newsAdapter.updateNewsList(it)
        })

        viewModel.newsListLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.txtError?.visibility = View.VISIBLE
            } else {
                binding.txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.recView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recView.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })
    }
}