package com.maverick.themelancholy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maverick.themelancholy.R
import com.maverick.themelancholy.databinding.FragmentHistoryBinding
import com.maverick.themelancholy.viewmodel.ListHistoryViewModel
import com.maverick.themelancholy.viewmodel.SharedViewModel

class HistoryFragment : Fragment() {
    private lateinit var binding:FragmentHistoryBinding
    private lateinit var sharedViewModel:SharedViewModel
    private lateinit var viewModel:ListHistoryViewModel
    private val newsAdapter = NewsAdapter(arrayListOf(), this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListHistoryViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel.currentUsernameLD.observe(viewLifecycleOwner, Observer {
            viewModel.refresh(it)
        })

        binding.recViewHistory.layoutManager = LinearLayoutManager(context)
        binding.recViewHistory.adapter = newsAdapter

        observeListHistoryViewModel()

        binding.refreshLayoutHistory.setOnRefreshListener {
            binding.recViewHistory.visibility = View.GONE
            binding.txtErrorHistory.visibility = View.GONE
            binding.progressLoadHistory.visibility = View.VISIBLE
            sharedViewModel.currentUsernameLD.observe(viewLifecycleOwner, Observer {
                viewModel.refresh(it)
            })
            binding.refreshLayoutHistory.isRefreshing = false
        }
    }

    fun observeListHistoryViewModel(){
        viewModel.newsHistoryLD.observe(viewLifecycleOwner, Observer {
            newsAdapter.updateNewsList(it)
        })

        viewModel.newsHistoryLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.txtErrorHistory?.visibility = View.VISIBLE
            } else {
                binding.txtErrorHistory?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.recViewHistory.visibility = View.GONE
                binding.progressLoadHistory.visibility = View.VISIBLE
            } else {
                binding.recViewHistory.visibility = View.VISIBLE
                binding.progressLoadHistory.visibility = View.GONE
            }
        })
    }
}