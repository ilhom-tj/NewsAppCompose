package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
            val scrollState = rememberScrollState()
            Scaffold(
                topBar = { NewsToolBar(modifier = modifier) },
            ) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    item {
                        HorizontalPager(state = pagerState) { page ->
                            TopNewsSliderItem(modifier)
                        }
                    }
                    item {
                        HorizontalPagerIndicator(
                            pagerState = pagerState,
                            modifier = Modifier
                                .padding(top = 5.dp),
                        )
                    }
                    item {
                        Box(
                            Modifier
                                .fillMaxWidth(0.9f)
                                .offset(y = 24.dp)
                                .height(1.dp)
                                .background(Color.Gray.copy(0.5f)),
                        )
                    }

                    val stathamList = listOf<Int>(1, 2, 3, 4)
                    items(stathamList.windowed(2, 2, true)) { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Red)
                        ) {
                            repeat(item.size) {
                                NewsArticles(modifier)
                            }
                        }

                    }


                }


            }

        }
    }
}

