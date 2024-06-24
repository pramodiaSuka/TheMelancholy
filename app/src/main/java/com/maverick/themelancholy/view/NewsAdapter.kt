package com.maverick.themelancholy.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.maverick.themelancholy.databinding.NewsListItemBinding
import com.maverick.themelancholy.model.News
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class NewsAdapter(val newsList:ArrayList<News>, val fragment: Fragment):RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(var binding:NewsListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.news = newsList[position]

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(newsList[position].image_url).into(holder.binding.cardImageNews, object:Callback{
            override fun onSuccess() {
                holder.binding.cardImageNews.visibility = View.VISIBLE
            }

            override fun onError(e: Exception?) {
                Log.e("picasso error", e.toString())
            }
        })

//        holder.binding.txtTitle.text = newsList[position].title
//        holder.binding.txtAuthor.text = newsList[position].users_username
//        holder.binding.txtDescription.text = newsList[position].description
        holder.binding.btnRead.setOnClickListener {
            if (fragment is HomeFragment){
                val action = newsList[position].id?.let { it1 ->
                    HomeFragmentDirections.actionDetailFragment(
                        it1
                    )
                }
                if (action != null) {
                    Navigation.findNavController(it).navigate(action)
                }
            }
            else if (fragment is HistoryFragment){
                val action = newsList[position].id?.let { it1 ->
                    HistoryFragmentDirections.actionDetailFragmentFromHistory(
                        it1
                    )
                }
                if (action != null) {
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }

    fun updateNewsList(newNewsList: ArrayList<News>){
        newsList.clear()
        newsList.addAll(newNewsList)
        notifyDataSetChanged()
    }
}