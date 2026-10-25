package ru.mirea.module2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Initials() {
    Box(
        modifier = Modifier.fillMaxWidth().height(100.dp).background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.padding(vertical = 16.dp)
                .fillMaxHeight().aspectRatio(1f)
                .background(Color(0xFFFF5722), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("АИ", fontSize = 24.sp, color = Color.Black)
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun InitialsPreview() {
    Initials()
}
