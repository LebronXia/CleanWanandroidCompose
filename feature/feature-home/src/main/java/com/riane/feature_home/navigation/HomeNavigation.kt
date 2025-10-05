package com.riane.feature_home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.riane.feature_home.ui.HomeScreen
import kotlinx.serialization.Serializable

object HomeRoutes {
    const val ROOT = "home_route"
    const val HOME = "home"
}

fun NavController.navigateToHome() = navigate(HomeRoutes.HOME)


fun NavGraphBuilder.homeSection(
    navController : NavHostController,
    onTopClick: (String) -> Unit,
){
    navigation(startDestination = HomeRoutes.HOME, route = HomeRoutes.ROOT){
        composable(route = HomeRoutes.HOME){
            HomeScreen(navCtrl = navController)
        }

    }
}
