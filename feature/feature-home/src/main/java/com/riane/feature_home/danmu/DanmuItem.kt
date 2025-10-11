package com.riane.feature_home.danmu

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kotlin.random.Random

data class DanmuItem(
    val id: Long = System.currentTimeMillis(),
    val text: String,
    val color: Color = randomColor(),
    val fontSize: TextUnit = (18 + Random.nextInt(16)).sp,
    val speed: Float = 1f + Random.nextFloat() * 2f,
    val offsetX: Float = 1f,
    val offsetY: Float = Random.nextFloat() * 0.8f,
    val isLocal: Boolean = false
) {

    companion object {
        fun randomColor() = Color(
            red = Random.nextFloat(), green = Random.nextFloat(), blue = Random.nextFloat(),
            alpha = 1f
        )
    }
}