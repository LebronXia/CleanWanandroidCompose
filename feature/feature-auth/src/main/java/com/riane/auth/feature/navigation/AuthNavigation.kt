package com.riane.auth.feature.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.riane.auth.feature.ui.LoginScreen
import kotlinx.serialization.Serializable


@Serializable public data object LoginRoute

fun NavGraphBuilder.loginSection(
    onTopClick: (String) -> Unit,
){
    navigation<LoginRoute>(startDestination = LoginRoute){
        composable<LoginRoute> {
            LoginScreen(onTopClick)
        }
    }
}