package com.example.newsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.models.Article

@Database(entities = [Article::class], version = 1)
abstract class NewsDB : RoomDatabase() {
    abstract fun getDao(): NewDao
}
