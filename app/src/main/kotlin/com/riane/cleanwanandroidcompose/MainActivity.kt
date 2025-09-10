package com.riane.cleanwanandroidcompose

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.core.view.WindowCompat
import com.riane.ui.CleanWanandroidComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()

        setContent {

            // statusBar图标颜色模式
//            val isDark = if (appState.iconIsLight && appState.currentDestination?.route == MAIN_ROUTE) true else AppThemeType.isDark(
//                themeType = lorenTheme
//            )

            val isDark = false;

            DisposableEffect(isDark) {
                enableEdgeToEdge(
                    SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT) { isDark },
                    SystemBarStyle.auto(Color.WHITE, Color.BLACK) { isDark },
                )
                onDispose { }
            }
            CleanWanandroidComposeTheme {
                WaApp()
            }
        }
    }
}


//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    CleanWanandroidComposeTheme {
//        Greeting("Android")
//    }
//}