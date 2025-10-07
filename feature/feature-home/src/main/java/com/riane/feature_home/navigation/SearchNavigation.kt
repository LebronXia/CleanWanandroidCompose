package com.riane.feature_home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.riane.feature_home.ui.HomeScreen
import com.riane.feature_home.ui.SearchScreen
import com.riane.ui.component.HomeSearchBar

object SearchRoutes {
    const val ROOT = "search_route"
    const val SEARCH = "search"
}

fun NavGraphBuilder.searchSection(
    navController: NavHostController,
    onTopClick: (String) -> Unit,
) {
    //https://blog.csdn.net/HugMua/article/details/130178076
    navigation(startDestination = SearchRoutes.SEARCH, route = SearchRoutes.ROOT) {
        composable(route = "${SearchRoutes.SEARCH}/{content}",
            arguments = listOf(navArgument("content") { NavType.StringType })
        ) { backStackEntry ->
            val content = backStackEntry.arguments?.getString("content")
            SearchScreen(content, navCtrl = navController)
        }

    }
}