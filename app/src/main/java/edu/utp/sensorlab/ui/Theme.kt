package edu.utp.sensorlab.ui

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Indigo = Color(0xFF6258E8)
val Violet = Color(0xFF8B5CF6)
val Amber = Color(0xFFF59E0B)
val Emerald = Color(0xFF0DAD86)
val Pink = Color(0xFFEC4899)
private val LightScheme = lightColorScheme(
    primary = Indigo, secondary = Violet,
    background = Color(0xFFF7F8FC), surface = Color.White,
    surfaceVariant = Color(0xFFEEF0F8),
    onBackground = Color(0xFF161B31), onSurface = Color(0xFF161B31)
)
private val DarkScheme = darkColorScheme(
    primary = Color(0xFFAAA5FF), secondary = Color(0xFFC1A7FF),
    background = Color(0xFF11131E), surface = Color(0xFF1C2030),
    surfaceVariant = Color(0xFF2A2F43)
)

@Composable
fun SensorLabTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = if (isSystemInDarkTheme()) DarkScheme else LightScheme, content = content)
}
