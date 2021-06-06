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

    val categories = listOf(
        "business",
        "entertainment",
        "health",
        "science",
        "sports",
        "technology"
    )
    val newsTop = newsDao.getNews("top")
    fun getNews(category: String) = newsDao.getNews(category = category)
    fun getTopNews() = GlobalScope.launch {

        api.getTopNews().enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    cacheNews(response.body()!!.articles,"top")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

                Log.e("ERROR", t.message.toString())
            }

        })
        categories.forEach {
            fillCategories(it)
        }
       // newsDao.addArticle(article)
    }

    fun cacheNews(list: List<Article>, category: String) = GlobalScope.launch {
        list.forEach {
            it.category = category
            newsDao.addArticle(it)
        }
    }

    fun fillCategories(category: String){
        api.getTopNews(category = category).enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    response.body()?.articles?.let {
                        Log.e("ARTICLE",it.get(0).title)
                        cacheNews(it,category) }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

            }

        })
    }
}