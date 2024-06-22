package com.maverick.themelancholy.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import buildDb
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.maverick.themelancholy.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class RegisterViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val regStatusLD = MutableLiveData<Boolean>()
//    val TAG = "volleyTag"
//    private var queue: RequestQueue? = null

    fun register(p_username:String, p_email:String, p_password:String, p_first_name:String, p_last_name:String, p_image_url:String){
        regStatusLD.value = false
        var newUser = User(username = p_username, email = p_email, password = p_password, first_name = p_first_name, last_name = p_last_name, image_url = p_image_url)

        launch {
            val db = buildDb(getApplication())

            db.userDao().InsertUser(newUser)
            regStatusLD.postValue(true)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

//    fun register(p_username:String, p_email:String, p_password:String, p_first_name:String, p_last_name:String, p_image_url:String){
//        //regStatusLD.value = false
//        var newUser = User(username = p_username, email = p_email, password = p_password, first_name = p_first_name, last_name = p_last_name, image_url = p_image_url)
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/WebProjects/Hobby/insertUser.php"
//        val stringRequest = object : StringRequest(
//            Request.Method.POST,
//            url,
//            Response.Listener {
//                Log.d("apiresult", it)
//                val obj = JSONObject(it)
//                if (obj.getString("result") == "OK"){
//                    regStatusLD.value = true
//                }
//            },
//            Response.ErrorListener {
//                Log.d("apiresult", it.message.toString())
//                regStatusLD.value = false
//            }
//        )
//        {
//            override fun getParams(): MutableMap<String, String>? {
//                val params = HashMap<String, String>()
//                params["username"] = newUser.username.toString()
//                params["email"] = newUser.email.toString()
//                params["password"] = newUser.password.toString()
//                params["first_name"] = newUser.first_name.toString()
//                params["last_name"] = newUser.last_name.toString()
//                params["image_url"] = newUser.image_url.toString()
//                return params
//            }
//        }
//
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//    }

//    override fun onCleared() {
//        super.onCleared()
//        queue?.cancelAll(TAG)
//    }
}