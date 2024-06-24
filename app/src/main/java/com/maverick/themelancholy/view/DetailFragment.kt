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
import com.maverick.themelancholy.R
import com.maverick.themelancholy.databinding.FragmentDetailBinding
import com.maverick.themelancholy.model.News
import com.maverick.themelancholy.viewmodel.DetailNewsViewModel
import com.maverick.themelancholy.viewmodel.ListNewsViewModel
import com.maverick.themelancholy.viewmodel.SharedViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class DetailFragment : Fragment() {
    private lateinit var binding:FragmentDetailBinding
    private lateinit var viewModel:DetailNewsViewModel
    private lateinit var sharedViewModel:SharedViewModel
    private var newsId = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null){
            newsId = DetailFragmentArgs.fromBundle(requireArguments()).newsId
        }
        viewModel = ViewModelProvider(this).get(DetailNewsViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel.currentUsernameLD.observe(viewLifecycleOwner, Observer { currentUsernameLD ->
            viewModel.fetch(newsId, currentUsernameLD)

        })

        observeDetailNewsViewModel()
    }

    fun indexCheck(index:Int, arraySize:Int){
        if (index == 0){
            binding.btnPreviousDetail.isEnabled = false
            binding.btnNextDetail.isEnabled = true
        }
        else if (index == (arraySize - 1)) {
            binding.btnNextDetail.isEnabled = false
            binding.btnPreviousDetail.isEnabled = true
        }
        else {
            binding.btnPreviousDetail.isEnabled = true
            binding.btnNextDetail.isEnabled = true
        }
    }
    fun observeDetailNewsViewModel(){
        viewModel.newsDetailLD.observe(viewLifecycleOwner, Observer {
            var currentNews = it

            binding.newswithpages = currentNews

//            binding.txtTitleDetail.text = currentNews.news.title
//            binding.txtAuthorDetail.text = currentNews.news.users_username

            var pagesCount = currentNews.pages?.size
            var currentPage = 0

            binding.page = currentNews.pages.get(currentPage)

            //binding.txtContentDetail.text = currentNews.pages?.get(currentPage)?.content.toString()

            val picasso = Picasso.Builder(requireContext())
            picasso.listener { picasso, uri, exception ->
                exception.printStackTrace()
            }
            picasso.build().load(currentNews.news.image_url).into(binding.detailNewsImage, object:
                Callback {
                override fun onSuccess() {
                    binding.detailNewsImage.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    Log.e("picasso error", e.toString())
                }
            })

            binding.btnPreviousDetail.isEnabled = false
            binding.btnNextDetail.isEnabled = true



            binding.btnNextDetail.setOnClickListener {
                currentPage += 1
                binding.page = currentNews.pages.get(currentPage)
//                binding.txtContentDetail.text = currentNews.pages?.get(currentPage)?.content.toString()
                indexCheck(currentPage, pagesCount!!)
            }
            binding.btnPreviousDetail.setOnClickListener {
                currentPage -= 1
                binding.page = currentNews.pages.get(currentPage)
//                binding.txtContentDetail.text = currentNews.pages?.get(currentPage)?.content.toString()
                indexCheck(currentPage, pagesCount!!)
            }
        })
    }

//    fun observeDetailNewsViewModel(){
//        viewModel.newsDetailLD.observe(viewLifecycleOwner, Observer {
//            var currentNews = it
//
//            binding.txtTitleDetail.text = currentNews.title
//            binding.txtAuthorDetail.text = currentNews.users_username
//
//            var pagesCount = currentNews.page?.size
//            var currentPage = 0
//
//            binding.txtContentDetail.text = currentNews.page?.get(currentPage)?.content.toString()
//
//            val picasso = Picasso.Builder(requireContext())
//            picasso.listener { picasso, uri, exception ->
//                exception.printStackTrace()
//            }
//            picasso.build().load(currentNews.image_url).into(binding.detailNewsImage, object:
//                Callback {
//                override fun onSuccess() {
//                    binding.detailNewsImage.visibility = View.VISIBLE
//                }
//
//                override fun onError(e: Exception?) {
//                    Log.e("picasso error", e.toString())
//                }
//            })
//
//            binding.btnPreviousDetail.isEnabled = false
//            binding.btnNextDetail.isEnabled = true
//
//
//
//            binding.btnNextDetail.setOnClickListener {
//                currentPage += 1
//                binding.txtContentDetail.text = currentNews.page?.get(currentPage)?.content.toString()
//                indexCheck(currentPage, pagesCount!!)
//            }
//            binding.btnPreviousDetail.setOnClickListener {
//                currentPage -= 1
//                binding.txtContentDetail.text = currentNews.page?.get(currentPage)?.content.toString()
//                indexCheck(currentPage, pagesCount!!)
//            }
//        })
//    }
}