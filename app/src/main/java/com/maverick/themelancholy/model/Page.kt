package com.maverick.themelancholy.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//id, content, news_id
@Entity
data class Page(
    var news_id:Int,
    val content:String?
)
{
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}
