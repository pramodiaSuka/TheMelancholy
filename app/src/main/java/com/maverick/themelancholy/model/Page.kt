package com.maverick.themelancholy.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//id, content, news_id
@Entity
data class Page(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    val news_id:Int,
    val content:String?
)
