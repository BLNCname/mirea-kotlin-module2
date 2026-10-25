package ru.mirea.module2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firstTask = intent.getIntExtra("task", 1)

        setContent {
            MaterialTheme(colorScheme = lightColorScheme()) {
                var task by rememberSaveable { mutableIntStateOf(firstTask) }

                Surface(Modifier.fillMaxSize(), color = Color.White) {
                    Column(Modifier.systemBarsPadding()) {
                        Text(
                            "Модуль 2 · Задание $task",
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                            for (number in listOf(1, 3, 4, 5, 10)) {
                                TextButton(onClick = { task = number }) {
                                    Text(number.toString())
                                }
                            }
                        }
                        HorizontalDivider()
                        Box(Modifier.fillMaxSize()) {
                            when (task) {
                                1 -> Hello(if (BuildConfig.NULL_NAME) null else "Алексей")
                                3 -> TextSamples()
                                4 -> StyledButton()
                                5 -> Initials()
                                10 -> ShoppingCartScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}
