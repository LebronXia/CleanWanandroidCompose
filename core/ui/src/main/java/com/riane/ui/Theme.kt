package com.riane.ui

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

enum class AppThemeType {
    FOLLOW_SYSTEM, Light, Dark;

    companion object {
        fun formatTheme(theme: Int? = 1): AppThemeType {
            entries.forEach {
                if (it.ordinal == theme) {
                    return it
                }
            }
            return Light
        }

        @Composable
        fun isDark(themeType: AppThemeType): Boolean {
            return when (themeType) {
                FOLLOW_SYSTEM -> isSystemInDarkTheme()
                Light -> false
                Dark -> true
            }
        }
    }
}

@Composable
fun CleanWanandroidComposeTheme(
    themeType: AppThemeType = AppThemeType.Light,
    content: @Composable () -> Unit
) {

    val colors =
        if (AppThemeType.isDark(themeType = themeType)) darkLorenColors else lightLorenColors
    CompositionLocalProvider(
        LocalCustomColors provides colors,
        LocalTextStyles provides CleanWanAndroidTheme.textStyle
    ) {
        MaterialTheme(content = content)
    }

    MaterialTheme(
        content = content
    )
}

object CleanWanAndroidTheme {
    val colors: LorenColors
        @Composable
        get() = LocalCustomColors.current
    val textStyle: LorenTextStyle
        @Composable
        get() = LocalTextStyles.current
}

