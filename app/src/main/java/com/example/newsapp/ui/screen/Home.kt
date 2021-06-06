package com.example.newsapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.components.ArticlesBig
import com.example.newsapp.ui.components.ArticlesList
import com.example.newsapp.ui.components.CategoryButtonsSmall
import com.example.newsapp.ui.components.TopNewsSliderItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun Home(modifier : Modifier) {
    val pagerState = rememberPagerState(
        pageCount = 4,
        initialOffscreenLimit = 2,
    )
    val scrollState = rememberScrollState()
    LazyColumn() {
        item{
            ToolBarTitle(title = "News")
        }
        item{
            LazyRow() {
                items(6){
                    CategoryButtonsSmall(title = "Abbos")
                }
            }
        }
        item{
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(5){
                    ArticlesBig()
                }
            }
        }
        items(5){
            ArticlesList()
        }

    }


}

@Composable
fun ToolBarTitle(title : String){
    Column {
        Text(text = title,
            modifier = Modifier.padding(start = 16.dp),
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Black,
                fontSize = 35.sp
            )
        )
        Spacer(Modifier.height(10.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .height(0.5.dp))
    }
}