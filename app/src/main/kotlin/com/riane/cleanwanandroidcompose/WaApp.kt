package com.riane.cleanwanandroidcompose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.riane.cleanwanandroidcompose.navigation.WaNavHost
import com.riane.cleanwanandroidcompose.ui.AppBottomBar
import com.riane.feature_home.navigation.HomeRoutes

@Composable
fun WaApp() {
    //路由管理
    val navController = rememberNavController()
    val mainAppState = rememberMainState(navController)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val shouldShowBottomBar = currentDestination?.route in setOf(HomeRoutes.HOME)

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),

        bottomBar = {
            //动画转场
            AnimatedVisibility(
                visible = shouldShowBottomBar,
                enter = expandVertically(
                    expandFrom = Alignment.Top
                ),
                exit = shrinkVertically(
                    shrinkTowards = Alignment.Top,
                )
            ) {
                AppBottomBar(navController, mainAppState)
            }
        }

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ){
            WaNavHost()
        }
    }

}

//fun shouldShowBottomBar(currentDestinattion: NavDestination?): Boolean{
//    if (currentDestinattion?.route == AuthBaseRoute.LoginRoute.toString()){
//        return false
//    }
//}
