package com.maverick.themelancholy.view

import android.icu.util.Calendar
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
import com.maverick.themelancholy.databinding.FragmentCreateNewsBinding
import com.maverick.themelancholy.model.News
import com.maverick.themelancholy.model.Page
import com.maverick.themelancholy.viewmodel.DetailNewsViewModel
import com.maverick.themelancholy.viewmodel.SharedViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import java.text.SimpleDateFormat

class CreateNewsFragment : Fragment(), NewsClickListener {
    private lateinit var binding: FragmentCreateNewsBinding
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var viewModel: DetailNewsViewModel
    private var currentUsername = "placeholder"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.news = News("","","","","")
        binding.listener = this
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel = ViewModelProvider(this).get(DetailNewsViewModel::class.java)
        observeDetailNewsViewModel()

//        binding.btnAddNews.setOnClickListener {
//            sharedViewModel.currentUsernameLD.observe(viewLifecycleOwner, Observer { currentUsernameLD ->
//                //Get Date to string
//                val today = Calendar.getInstance()
//                var dateFormat = SimpleDateFormat("yyyy-MM-dd")
//                var dateStr = dateFormat.format(today.time)
//
//
//                var news = News(binding.txtTitleCreate.text.toString(), binding.txtDescriptionCreate.text.toString(), binding.txtImageUrl.text.toString(), dateStr, currentUsernameLD)
//                var pagesString = binding.txtContentCreate.text.toString().split("\n")
//                var pages:ArrayList<Page> = arrayListOf()
//                for (i in 0 until pagesString.size){
//                    var newPage = Page(0, pagesString[i])
//                    pages.add(newPage)
//                }
//                viewModel.createNews(news, pages)
//                //Toast.makeText(requireContext(), "${pages.size} ${dateStr} $currentUsernameLD", Toast.LENGTH_SHORT).show()
//            })
//        }

    }


    fun observeDetailNewsViewModel(){
        viewModel.createStatusLD.observe(viewLifecycleOwner, Observer{
            if (it == true){
                Toast.makeText(requireContext(), "News Created Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Create News Failed!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onNewsClick(v: View) {
        sharedViewModel.currentUsernameLD.observe(viewLifecycleOwner, Observer { currentUsernameLD ->
            //Get Date to string
            val today = Calendar.getInstance()
            var dateFormat = SimpleDateFormat("yyyy-MM-dd")
            var dateStr = dateFormat.format(today.time)


            var news = News(binding.news!!.title, binding.news!!.description, binding.news!!.image_url, dateStr, currentUsernameLD)
            var pagesString = binding.txtContentCreate.text.toString().split("\n")
            var pages:ArrayList<Page> = arrayListOf()
            for (i in 0 until pagesString.size){
                var newPage = Page(0, pagesString[i])
                pages.add(newPage)
            }
            viewModel.createNews(news, pages)
        })
    }
}