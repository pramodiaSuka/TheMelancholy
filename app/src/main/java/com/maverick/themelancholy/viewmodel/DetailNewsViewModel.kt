package com.maverick.themelancholy.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import buildDb
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maverick.themelancholy.model.News
import com.maverick.themelancholy.model.Page
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class DetailNewsViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val newsDetailLD = MutableLiveData<News>()
    val newsDetailLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val createStatusLD = MutableLiveData<Boolean>()
//    val TAG = "volleyTag"
//    private var queue: RequestQueue? = null

    fun createNews(news: News, pages:ArrayList<Page>){
        createStatusLD.value = false
        launch {
            val db = buildDb(getApplication())

            db.newsDao().InsertNewsWithPage(news, pages)
            createStatusLD.postValue(true)
        }
    }
//    fun fetch(id:Int, username:String){
//        newsDetailLoadErrorLD.value = false
//        loadingLD.value = true
//        queue = Volley.newRequestQueue((getApplication()))
//        val url = "http://10.0.2.2/WebProjects/Hobby/getNewsById.php"
//        val stringRequest = object : StringRequest(
//            Request.Method.POST, url,
//            Response.Listener
//            {
//                val obj = JSONObject(it)
//                if (obj.getString("result") == "OK"){
//                    val data = obj.getJSONArray("data")
//                    val sType = object : TypeToken<List<News>>(){ }.type
//                    val result = Gson().fromJson<List<News>>(data.toString(), sType) as ArrayList<News>
//                    newsDetailLD.value = result[0]
//                    loadingLD.value = false
//                    Log.d("showNews", result[0].toString())
//                }
//
//            },
//            Response.ErrorListener{
//                Log.d("showNews", it.toString())
//                newsDetailLoadErrorLD.value = true
//                loadingLD.value = false
//            }
//        )
//        {
//            override fun getParams(): MutableMap<String, String>? {
//                val params = HashMap<String, String>()
//                params["id"] = id.toString()
//                params["username"] = username
//                return params
//            }
//        }
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

//    fun fetch(id:Int, username:String){
//        newsDetailLoadErrorLD.value = false
//        loadingLD.value = true
//        queue = Volley.newRequestQueue((getApplication()))
//        val url = "http://10.0.2.2/WebProjects/Hobby/getNewsById.php"
//        val stringRequest = object : StringRequest(
//            Request.Method.POST, url,
//            Response.Listener
//            {
//                val obj = JSONObject(it)
//                if (obj.getString("result") == "OK"){
//                    val data = obj.getJSONArray("data")
//                    val sType = object : TypeToken<List<News>>(){ }.type
//                    val result = Gson().fromJson<List<News>>(data.toString(), sType) as ArrayList<News>
//                    newsDetailLD.value = result[0]
//                    loadingLD.value = false
//                    Log.d("showNews", result[0].toString())
//                }
//
//            },
//            Response.ErrorListener{
//                Log.d("showNews", it.toString())
//                newsDetailLoadErrorLD.value = true
//                loadingLD.value = false
//            }
//        )
//        {
//            override fun getParams(): MutableMap<String, String>? {
//                val params = HashMap<String, String>()
//                params["id"] = id.toString()
//                params["username"] = username
//                return params
//            }
//        }
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//    }

//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }
}