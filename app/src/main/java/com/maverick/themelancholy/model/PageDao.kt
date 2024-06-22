package com.maverick.themelancholy.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertPage(vararg page: Page)

    @Query("SELECT * FROM page")
    fun GetAllPage(): List<Page>

    @Update
    fun UpdatePage(page: Page)
    @Delete
    fun DeletePage(page: Page)
}