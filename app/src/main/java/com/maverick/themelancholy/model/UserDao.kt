package com.maverick.themelancholy.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertUser(vararg user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertHistory(history: UserNewsCrossRef)

    @Query("SELECT * FROM user WHERE username =:username AND password=:password")
    fun LoginCheck(username:String, password:String): User

    @Transaction
    @Query("SELECT * FROM user WHERE username =:username")
    fun GetReadHistory(username: String): UsersWithNews

    @Query("SELECT * FROM user WHERE username =:username")
    fun GetUser(username:String): User

    @Update
    fun UpdateUser(user: User)
    @Delete
    fun DeleteUser(user: User)

}