package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.components.NewsArticles
import com.example.newsapp.ui.components.NewsToolBar
import com.example.newsapp.ui.components.TopNewsSliderItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val modifier = Modifier.padding(16.dp)
        setContent {
            val pagerState = rememberPagerState(
                pageCount = 4,
                initialOffscreenLimit = 2,
            )

            Scaffold(
                topBar = { NewsToolBar(modifier = modifier) },
            ) {
                Column {
                    HorizontalPager(state = pagerState) { page ->
                        TopNewsSliderItem(modifier)
                    }
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier
                            .padding(top = 5.dp),
                    )
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2)
                    ) {
                        items(4) {
                            NewsArticles()
                        }
                    }
                }
            }
        }
    }
}

