package ru.mirea.module2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CornerCircle() {
    Box(Modifier.size(240.dp, 120.dp).background(Color.Black)) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = "Оранжевый круг",
            modifier = Modifier.align(Alignment.TopEnd).size(48.dp)
        )
    }
}

@Composable
fun StretchedCircle() {
    Box(Modifier.size(240.dp, 120.dp).background(Color.Blue)) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = "Фиолетовый круг",
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color(0xFF7C2B86))
        )
    }
}

@Composable
fun CircleExamples() {
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Круг в углу")
        CornerCircle()
        Text("Круг по центру")
        StretchedCircle()
    }
}
