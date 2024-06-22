package com.maverick.themelancholy.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertNews(vararg news: News)

    @Query("SELECT * FROM news")
    fun GetAllNews(): List<News>

    @Transaction
    @Query("SELECT * FROM news WHERE id=:newsId")
    fun GetNewsWithId(newsId: Int): List<NewsWithPages>

    @Update
    fun UpdateNews(news: News)
    @Delete
    fun DeleteNews(news: News)
}