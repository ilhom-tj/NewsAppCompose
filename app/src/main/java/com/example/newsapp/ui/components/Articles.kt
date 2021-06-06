package com.example.newsapp.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.newsapp.R
import com.example.newsapp.models.Article
import com.example.newsapp.ui.theme.LightColorPalette
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.glide.rememberGlidePainter

@ExperimentalMaterialApi
@Composable
fun ArticlesBig(
    article: Article,
    navController: NavController
) {
    fun navigate(category : String){
        navController.navigate("newsList"){
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val colors = listOf(
        Color.Transparent,
        Color.Black
    )
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(10.dp)
            .requiredWidth(300.dp)
            .requiredHeight(280.dp),
        onClick = {
            article.category?.let { navigate(category = it) }
        }
    ) {
        if (article.urlToImage != null){
            Image(
                painter = rememberGlidePainter(request = article.urlToImage),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
            )
        }
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = colors,
                        startY = 400f
                    )
                )
        )
        val constraintSet = ConstraintSet {
            val category = createRefFor("category")
            val title = createRefFor("title")
            constrain(category) {
                top.linkTo(parent.top)
            }
            constrain(title) {
                bottom.linkTo(parent.bottom)
            }
        }
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            constraintSet = constraintSet
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .wrapContentSize()
                    .background(LightColorPalette.primary)
                    .padding(5.dp)
                    .layoutId("category"),
            ) {
                article.category?.let {
                    Text(
                        text = it,
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            Column(
                modifier = Modifier
                    .layoutId("title")
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = article.title,
                    style = TextStyle(
                        Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_access_time_24),
                        contentDescription = "",
                        Modifier.size(20.dp),
                        colorFilter = ColorFilter.tint(Color.Red)
                    )
                    Text(
                        text = "Apr 26, 2021",
                        style = TextStyle(Color.White.copy(.5f)),
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
        }


    }
}

@Composable
fun ArticlesList(article: Article) {
    Row(
        Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        val constraintSet = ConstraintSet {
            val title = createRefFor("title")
            val author = createRefFor("author")
            val image = createRefFor("img")
            constrain(title) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            constrain(author) {
                top.linkTo(title.bottom)
                start.linkTo(title.start)
            }
            constrain(image) {
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                top.linkTo(parent.top)
                start.linkTo(title.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }

        }
        ConstraintLayout(constraintSet, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = article.title,
                style = TextStyle(
                    Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(end = 10.dp)
                    .layoutId("title")
            )
            article.author?.let {
                Text(
                    text = it,
                    style = TextStyle(
                        Color.Black
                    ),
                    modifier = Modifier
                        .width(250.dp)
                        .padding(end = 5.dp)
                        .layoutId("author")
                )
            }
            if (article.urlToImage != null){
                Image(
                    painter = rememberCoilPainter(request = article.urlToImage!!),
                    contentDescription = "",
                    Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .layoutId("img"),
                    contentScale = ContentScale.Crop
                )
            }

        }
    }
}

@Composable
fun ArticlesGrid(article: Article) {
    Row(
        Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        val constraintSet = ConstraintSet {
            val title = createRefFor("title")
            val image = createRefFor("img")
            constrain(title) {
                top.linkTo(image.bottom)
                start.linkTo(image.start)
                end.linkTo(image.end)
                width = Dimension.fillToConstraints
            }
            constrain(image) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.value(180.dp)
            }

        }
        ConstraintLayout(constraintSet, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = article.title,
                style = TextStyle(
                    Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .padding(start = 10.dp)
                    .layoutId("title")
            )
//            Log.e("img",article.urlToImage!!)
            if (article.urlToImage != null){
                Image(
                    painter = rememberCoilPainter(request = article.urlToImage),
                contentDescription = "",
                Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .layoutId("img"),
                contentScale = ContentScale.Crop
                )
            }

        }
    }
}

