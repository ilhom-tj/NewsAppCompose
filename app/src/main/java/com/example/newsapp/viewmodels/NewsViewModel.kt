package com.example.newsapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.newsapp.networking.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    fun scratchNews() {
        Log.e("dsa","dsa")
        repository.getNewsByCategory("sports")
    }
}