package com.riane.cleanwanandroidcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.riane.auth.feature.navigation.AuthRoutes
import com.riane.auth.feature.navigation.loginSection
import com.riane.feature_home.navigation.homeSection


@Composable
fun WaNavHost(
    navController: NavHostController = rememberNavController(),
) {

    NavHost(navController = navController,
        startDestination = AuthRoutes.ROOT
        ) {
        loginSection(
            onTopClick = {}
        )
        homeSection(
            onTopClick = {}
        )

    }

}