package com.example.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.models.Article

@Dao
interface NewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: Article)

    @Query("SELECT * FROM news where category=:category")
    fun getNews(category: String) : LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}