package com.maverick.themelancholy.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maverick.themelancholy.model.User
import org.json.JSONObject

class LoginViewModel(application: Application): AndroidViewModel(application) {
    val currentUser = MutableLiveData<User?>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun login(p_username:String, p_password:String){
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/WebProjects/Hobby/loginUser.php"
        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            Response.Listener {
                Log.d("apiresult", it)
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK"){
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<List<User>>(){ }.type
                    val result = Gson().fromJson(data.toString(), sType) as ArrayList<User>

                    currentUser.value = result[0]

                    Log.d("apiresult", result[0].toString())
                }
                else{
                    currentUser.value = null
                }
            },
            Response.ErrorListener {
                Log.d("apiresult", it.message.toString())
            }
        )
        {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["username"] = p_username
                params["password"] = p_password
                return params
            }
        }

        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }
}