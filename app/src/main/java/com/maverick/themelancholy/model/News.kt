package com.maverick.themelancholy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//id, title, description, image_url, created_at, users_username
@Entity
data class News(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val title:String?,
    val description:String?,
    val image_url:String?,
    val created_at:String?,
    val users_username:String?)
//    val page:List<Page>?)
