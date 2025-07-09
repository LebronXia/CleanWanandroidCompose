package com.riane.feature_home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.riane.feature_home.ui.HomeScreen
import kotlinx.serialization.Serializable

object HomeRoutes {
    const val ROOT = "home_route"
    const val HOME = "home"
    const val REGISTER = "register"
}

//@Serializable
//sealed class HomeBaseRoute {
//    @Serializable
//    object HomeRoute : HomeBaseRoute()
//}

fun NavController.navigateToHome() = navigate(HomeRoutes.HOME)


fun NavGraphBuilder.homeSection(
    onTopClick: (String) -> Unit,
){
    navigation(startDestination = HomeRoutes.HOME, route = HomeRoutes.ROOT){
        composable(route = HomeRoutes.HOME){
            HomeScreen()
        }

    }
}
