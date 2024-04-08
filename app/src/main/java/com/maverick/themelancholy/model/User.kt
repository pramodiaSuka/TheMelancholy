package com.maverick.themelancholy.model

data class User(
    var username:String? = "",
    var email:String? = "",
    var password:String? = "",
    var first_name:String? = "",
    var last_name:String? = "",
    var created_at:String? = "",
    var updated_at:String? = "",
    var image_url:String? = ""
)
//username, email, password, first_name, last_name, created_at, updated_at, image_url
