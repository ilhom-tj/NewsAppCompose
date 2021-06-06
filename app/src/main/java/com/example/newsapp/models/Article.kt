package com.example.newsapp.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class Article(
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    @Embedded
    val source: Source,
    @PrimaryKey(autoGenerate = false)
    val title: String ,
    val url: String? = "",
    val urlToImage: String? = "",
    var category : String? = ""
)