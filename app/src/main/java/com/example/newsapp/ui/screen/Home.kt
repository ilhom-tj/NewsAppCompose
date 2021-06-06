package com.example.newsapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapp.ui.components.ArticlesBig
import com.example.newsapp.ui.components.ArticlesList
import com.example.newsapp.ui.components.CategoryButtonsSmall
import com.example.newsapp.viewmodels.NewsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Home(
    modifier: Modifier,
    viewModel : NewsViewModel,
    navController: NavController
) {
    val pagerState = rememberPagerState(
        pageCount = 4,
        initialOffscreenLimit = 2,
    )
    val scrollState = rememberScrollState()
    val topNews = viewModel.newsTop.observeAsState()


    LazyColumn() {
        item {
            ToolBarTitle(title = "News")
        }
        item {

            LazyRow() {
                val categories = listOf(
                    "Business",
                    "Entertainment",
                    "Health",
                    "Science",
                    "Sports",
                    "Technology"
                )
                items(categories) {
                    CategoryButtonsSmall(title = it) {
                        navController.navigate("newsList/$it")
                    }
                }
            }
        }
        if (topNews.value != null){
            item{
                LazyRow() {
                    itemsIndexed(topNews.value!!){index,item->
                        if (index < 5){
                            ArticlesBig(article = item,navController = navController)
                        }
                    }
                }

            }
            itemsIndexed(topNews.value!!){index , item ->
                if (index > 5){
                    ArticlesList(article = item)
                }
            }
        }
        
        item {
           Spacer(modifier = Modifier.height(80.dp))
        }

    }


}

@Composable
fun ToolBarTitle(title: String) {
    Column {
        Text(
            text = title,
            modifier = Modifier.padding(start = 16.dp),
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Black,
                fontSize = 35.sp
            )
        )
        Spacer(Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .height(0.5.dp)
        )
    }
}