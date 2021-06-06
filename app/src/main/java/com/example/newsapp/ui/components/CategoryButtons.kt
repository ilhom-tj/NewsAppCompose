package com.example.newsapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp.ui.theme.LightColorPalette

@Composable
fun CategoryButtonsSmall(title: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp, top = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xfffce4ec))
            .clickable(onClick = onClick),

    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 15.dp,end = 15.dp,top = 5.dp,bottom = 5.dp),
            style = TextStyle(
                LightColorPalette.primary,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        )

    }
}