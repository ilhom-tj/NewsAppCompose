package com.example.newsapp.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.viewmodels.NewsViewModel
import com.example.newsapp.R
import com.example.newsapp.ui.components.ArticlesBig
import com.example.newsapp.ui.components.ArticlesGrid
import com.example.newsapp.ui.components.ArticlesList
import java.util.*

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun NewsListScreen(category: String, viewModel: NewsViewModel) {
    val list = viewModel.getNews(category = category.toLowerCase()).observeAsState()
    val listOrGrid = MutableLiveData<Boolean>()

    val listClick = {
        listOrGrid.value = true
    }
    val gridClick = {
        listOrGrid.value = false
    }
    listOrGrid.observeAsState().value.let {
        Column() {
            TopListScreen(title = category,listClick,gridClick)
            if (list.value != null) {
                if (it == true){
                    LazyColumn() {
                        items(list.value!!) {
                            ArticlesList(article = it)
                        }
                    }
                }else{
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2)
                    ) {
                        items(list.value!!) { items ->
                            ArticlesGrid(items)
                        }
                    }
                }

            }
            Spacer(Modifier.height(80.dp))
        }
    }

}

@Composable
fun TopListScreen(title: String? = "Category",listClick : () -> Unit,gridClick : () ->Unit) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            title?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding(start = 16.dp),
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Black,
                        fontSize = 35.sp
                    )
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_list_24),
                    contentDescription = "",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .clickable(onClick = listClick),
                    colorFilter = ColorFilter.tint(Color.Black)

                )
                Image(
                    painter = painterResource(id = R.drawable.ic_outline_border_all_24),
                    contentDescription = "",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .clickable(onClick = gridClick),

                    colorFilter = ColorFilter.tint(Color.Black)
                )
            }
        }

        Spacer(Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .height(0.5.dp)
        )
    }
}