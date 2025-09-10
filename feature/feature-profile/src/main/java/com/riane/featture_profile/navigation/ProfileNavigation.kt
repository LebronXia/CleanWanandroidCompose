package com.riane.featture_profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.riane.featture_profile.ui.MineScreen
import com.riane.featture_profile.ui.ProfileScreen

object ProfileRoutes {
    const val ROOT = "profile_route"
    const val MINE = "mine"
}

fun NavController.navigateToMine() = navigate(ProfileRoutes.MINE)


fun NavGraphBuilder.profileSection(
    onTopClick: (String) -> Unit,
){
    navigation(startDestination = ProfileRoutes.MINE, route = ProfileRoutes.ROOT){
        composable(route = ProfileRoutes.MINE){
            ProfileScreen()
            //MineScreen()
        }

    }
}
