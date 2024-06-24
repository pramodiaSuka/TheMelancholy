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
    fun InsertNews(news: News): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertPage(vararg page: Page)

    @Transaction
    fun InsertNewsWithPage(news: News, pages: ArrayList<Page>){
        val newsId = InsertNews(news)
        pages.forEach { it.news_id = newsId.toInt() }
        InsertPage(*pages.toTypedArray())
    }

    @Query("SELECT * FROM news ORDER BY created_at DESC")
    fun GetAllNews(): List<News>

    @Transaction
    @Query("SELECT * FROM news WHERE id=:newsId")
    fun GetNewsWithId(newsId: Int): NewsWithPages

    @Update
    fun UpdateNews(news: News)
    @Delete
    fun DeleteNews(news: News)
}