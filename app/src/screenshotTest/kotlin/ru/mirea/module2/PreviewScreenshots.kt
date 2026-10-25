package ru.mirea.module2

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest

@PreviewTest
@Preview(
    name = "portrait", showSystemUi = true,
    widthDp = 360, heightDp = 640
)
@Composable
fun PortraitScreenshot() {
    HelloPreview()
}

@PreviewTest
@Preview(
    name = "landscape", showSystemUi = true,
    widthDp = 640, heightDp = 360
)
@Composable
fun LandscapeScreenshot() {
    HelloLandscapePreview()
}

@PreviewTest
@Preview(
    name = "round", showSystemUi = true,
    widthDp = 200, heightDp = 200,
    device = Devices.WEAR_OS_SMALL_ROUND,
    showBackground = true, backgroundColor = 0xFFFFFF00
)
@Composable
fun RoundScreenshot() {
    HelloRoundPreview()
}

@PreviewTest
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun TextScreenshot() {
    MaterialTheme { TextSamples() }
}
