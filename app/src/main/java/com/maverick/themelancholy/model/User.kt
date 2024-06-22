package com.maverick.themelancholy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//username, email, password, first_name, last_name, created_at, updated_at, image_url
@Entity
data class User(
    @PrimaryKey(autoGenerate = false)
    var username:String = "",
    @ColumnInfo(name = "email")
    var email:String? = "",
    @ColumnInfo(name = "password")
    var password:String? = "",
    @ColumnInfo(name = "first_name")
    var first_name:String? = "",
    @ColumnInfo(name = "last_name")
    var last_name:String? = "",
    @ColumnInfo(name = "created_at")
    var created_at:String? = "",
    @ColumnInfo(name = "updated_at")
    var updated_at:String? = "",
    @ColumnInfo(name = "image_url")
    var image_url:String? = ""
)
//username, email, password, first_name, last_name, created_at, updated_at, image_url
