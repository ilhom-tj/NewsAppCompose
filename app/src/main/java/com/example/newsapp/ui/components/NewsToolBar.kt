package com.example.newsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.R

@Composable
fun NewsToolBar(modifier: Modifier) {
    Column( modifier = modifier.fillMaxWidth()) {
        Text(
            text = "WEDNESDAY, JUNE 3",
            style = TextStyle(
                Color.Gray,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween , verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Top News",
                style = TextStyle(
                    Color.Black,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Black
                )
            )
            Image(
                painter = painterResource(id = R.mipmap.statham),
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(
                        width = 0.5.dp, Color.Black,
                        CircleShape
                    ),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }


    }
}
