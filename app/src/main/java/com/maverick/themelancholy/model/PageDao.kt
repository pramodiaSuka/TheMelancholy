package com.maverick.themelancholy.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PageDao {


    @Query("SELECT * FROM page")
    fun GetAllPage(): List<Page>

    @Update
    fun UpdatePage(page: Page)
    @Delete
    fun DeletePage(page: Page)
}