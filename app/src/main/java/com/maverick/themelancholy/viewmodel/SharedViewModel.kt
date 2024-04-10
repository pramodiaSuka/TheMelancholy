package com.maverick.themelancholy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SharedViewModel(application: Application):AndroidViewModel(application) {
    private var _currentUsername = MutableLiveData<String>()
    var currentUsernameLD:LiveData<String> = _currentUsername

    fun setCurrentUsername(username:String){
        _currentUsername.value = username
    }
}