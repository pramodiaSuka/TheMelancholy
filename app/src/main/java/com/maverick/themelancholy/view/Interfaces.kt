package com.maverick.themelancholy.view

import android.view.View

interface UserClickListener { //Untuk Login dan logout
    fun onUserClick(v: View)
}

interface UserRegisterClickListener { //Untuk Register
    fun onUserRegisterClick(v: View)
}

interface UserEditClickListener { //Untuk Login
    fun onUserEditClick(v: View)
}

interface NewsClickListener {
    fun onNewsClick(v: View)
}

interface NavigationClickListener {
    fun onNavigationClick(v: View)
}
