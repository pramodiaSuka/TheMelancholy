package com.maverick.themelancholy.model
data class News(
    val id:Int?,
    val title:String?,
    val description:String?,
    val image_url:String?,
    val created_at:String?,
    val users_username:String?,
    val page:List<Page>?)
