package com.example.newsapp.networking

import android.util.Log
import com.example.newsapp.db.NewDao
import com.example.newsapp.db.NewsDB
import com.example.newsapp.models.Article
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.models.Source
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val newsDao: NewDao,
    private val db : NewsDB
) {
    val api = RetrofitInstance().api()
    fun getNewsByCategory(category: String) = GlobalScope.launch {
        Log.e("AJAJAJAJ", "JAJAJJA")
        val source = Source(1, "dsa")
        val article = Article(
            "dsadsa", "dsadsa",
            "dsadsa", "dsadsa",
            source, "dsadsa", "dsadsa", "dsadsa", "dsadsa"
        )
        newsDao.addArticle(article)
    }

    fun log() {
        Log.e("dadsa", "ilhom")
    }

    fun cacheNews(list: List<Article>, category: String) = GlobalScope.launch {
        list.forEach {
            it.category = category
            newsDao.addArticle(it)
        }
    }
}