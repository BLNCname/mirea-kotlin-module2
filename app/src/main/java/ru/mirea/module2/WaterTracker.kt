package ru.mirea.module2

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WaterTracker() {
    val context = LocalContext.current
    val preferences = remember(context) {
        context.getSharedPreferences("water_tracker", Context.MODE_PRIVATE)
    }
    var waterCount by remember { mutableIntStateOf(preferences.getInt("water_ml", 100)) }
    var days by remember { mutableIntStateOf(preferences.getInt("days", 0)) }
    val primaryColor = Color(0xFF395886)

    fun saveProgress() {
        preferences.edit().putInt("water_ml", waterCount).putInt("days", days).apply()
    }

    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Трекер воды", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = primaryColor)
        Spacer(Modifier.height(24.dp))
        Text("$waterCount мл", fontSize = 48.sp, fontWeight = FontWeight.Bold, color = Color(0xFF515D70))
        Spacer(Modifier.height(16.dp))
        Text("Дней подряд с 1500 мл и больше: $days", textAlign = TextAlign.Center)
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                waterCount += 250
                saveProgress()
            },
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("+250 мл", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        OutlinedButton(
            onClick = {
                days = if (waterCount >= 1500) days + 1 else 0
                waterCount = 0
                saveProgress()
            },
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("Завершить день", color = primaryColor)
        }
    }
}
