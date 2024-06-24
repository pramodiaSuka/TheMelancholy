package com.maverick.themelancholy.model

import DB_NAME
import MIGRATION_1_2
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, News::class, Page::class, UserNewsCrossRef::class], version = 2)
abstract class MelancholyDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun newsDao(): NewsDao
    abstract fun pageDao(): PageDao

    companion object{
        @Volatile private var instance: MelancholyDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MelancholyDatabase::class.java,
                DB_NAME
            ).addMigrations(MIGRATION_1_2).build()

        operator fun invoke(context: Context){
            if (instance != null){
                synchronized(LOCK){
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}