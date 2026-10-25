package ru.mirea.module2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "portrait", showSystemUi = true,
    widthDp = 360, heightDp = 640
)
@Composable
fun HelloPreview() {
    MaterialTheme { Hello("Алексей") }
}

@Preview(
    name = "landscape", showSystemUi = true,
    widthDp = 640, heightDp = 360
)
@Composable
fun HelloLandscapePreview() {
    HelloPreview()
}

@Preview(
    name = "round", showSystemUi = true,
    widthDp = 200, heightDp = 200,
    device = Devices.WEAR_OS_SMALL_ROUND,
    showBackground = true, backgroundColor = 0xFFFFFF00
)
@Composable
fun HelloRoundPreview() {
    MaterialTheme {
        Box(Modifier.fillMaxSize().background(Color.Yellow)) {
            Hello("Аня")
        }
    }
}
