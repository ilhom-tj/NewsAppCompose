package com.example.newsapp.networking

import com.example.newsapp.models.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("top-headlines")
    fun getNewsByCategory(
        @Query("category") category: String,
        @Query("country") country : String = "us",
        @Query("apiKey") api: String = "a2c13b132cd64160bb53d5ce33ed7440"
    ) : Call<NewsResponse>
}
