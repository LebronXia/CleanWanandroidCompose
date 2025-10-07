package com.riane.cleanwanandroidcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.riane.auth.feature.navigation.AuthRoutes
import com.riane.auth.feature.navigation.loginSection
import com.riane.featture_profile.navigation.profileSection
import com.riane.feature_home.navigation.HomeRoutes
import com.riane.feature_home.navigation.homeSection
import com.riane.feature_home.navigation.searchSection


@Composable
fun WaNavHost(
    navController: NavHostController = rememberNavController(),
) {

    NavHost(navController = navController,
        startDestination = HomeRoutes.ROOT
        ) {
        loginSection(
            onTopClick = {}
        )
        homeSection(
            navController = navController,
            onTopClick = {}
        )
        profileSection(
            onTopClick = {}
        )
        searchSection(
            navController = navController,
            onTopClick = {}
        )

    }

}