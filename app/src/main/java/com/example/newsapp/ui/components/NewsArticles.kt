package com.example.newsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R

val randomColors = listOf(Color.Black, Color.Yellow, Color.Blue, Color.Green, Color.Magenta)

@Composable
fun NewsArticles() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(color = randomColors.random())
    ) {
        Image(
            painter = painterResource(id = R.mipmap.stathamslider),
            contentDescription = "",
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "STARTUPS",
            style = TextStyle(
                Color.Blue,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Statham today become bald. This will be breaking news",
            style = TextStyle(
                Color.Black,
                fontWeight = FontWeight.Black,
                fontSize = 18.sp
            )
        )
    }
}