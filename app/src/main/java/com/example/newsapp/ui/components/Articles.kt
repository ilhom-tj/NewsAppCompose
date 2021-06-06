package com.example.newsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import com.example.newsapp.R
import com.example.newsapp.ui.theme.LightColorPalette

@Composable
fun ArticlesBig() {
    val colors = listOf(
        Color.Transparent,
        Color.Black
    )
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(10.dp)
            .requiredWidth(300.dp)
            .requiredHeight(280.dp)

    ) {
        Image(
            painter = painterResource(id = R.mipmap.abba),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
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
                Text(
                    text = "Category",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Column(
                modifier = Modifier
                    .layoutId("title")
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = "BalX team is go on. The founder and team is working so hard!",
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

@Preview
@Composable
fun ArticlesList() {
    Row(
        Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(10.dp)) {
        val constraintSet = ConstraintSet {
            val title = createRefFor("title")
            val author = createRefFor("author")
            val image = createRefFor("img")
            constrain(title){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
            constrain(author){
                top.linkTo(title.bottom)
                start.linkTo(title.start)
            }
            constrain(image){
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                top.linkTo(parent.top)
                start.linkTo(title.end)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }

        }
        ConstraintLayout(constraintSet,modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "BalX team is go on. The founder and team is working so hard!",
                style = TextStyle(
                    Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(end = 10.dp)
                    .layoutId("title")
            )
            Text(
                text = "BalX News LLC",
                style = TextStyle(
                    Color.Black
                ),

                modifier = Modifier
                    .padding(end = 5.dp)
                    .layoutId("author")
            )
            Image(
                painter = painterResource(id = R.mipmap.abba),
                contentDescription = "",
                Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .layoutId("img"),
                contentScale = ContentScale.Crop
            )
        }


    }
}

