package com.maverick.themelancholy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//id, title, description, image_url, created_at, users_username
@Entity
data class News(
    var title:String?,
    var description:String?,
    var image_url:String?,
    val created_at:String?,
    val users_username:String?)
{
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}
//    val page:List<Page>?)
