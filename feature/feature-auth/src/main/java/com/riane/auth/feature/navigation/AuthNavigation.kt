package com.riane.auth.feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.riane.auth.feature.ui.LoginScreen
import kotlinx.serialization.Serializable

//@Serializable
//sealed class AuthBaseRoute {
//    // 子路由：定义具体的屏幕
//    @Serializable
//    object LoginRoute : AuthBaseRoute()
//    @Serializable
//    object RegisterRoute : AuthBaseRoute()
//}

object AuthRoutes {
    const val ROOT = "auth_route"
    const val LOGIN = "login"
    const val REGISTER = "register"
}


fun NavController.navigateToLogin(navOptions: NavOptions) = navigate(AuthRoutes.LOGIN, navOptions)


fun NavGraphBuilder.loginSection(
    onTopClick: (String) -> Unit,
){
    //通过基类路由，可以直观看出哪些路由属于同一功能模块，便于后续维护。
    navigation(startDestination = AuthRoutes.LOGIN, route = AuthRoutes.ROOT){
        composable(route = AuthRoutes.LOGIN) {
            LoginScreen(onTopClick)
        }

    }
}

//// 定义模块入口路由
//sealed class MainRoute(val route: String) {
//    object Home : MainRoute("home")
//    object Profile : MainRoute("profile")
//}
//
//NavHost(navController, startDestination = MainRoute.Home.route) {
//    // 首页模块子导航图
//    navigation(
//        startDestination = "home/main",
//        route = MainRoute.Home.route  // 父导航路由
//    ) {
//        composable("home/main") { HomeMainScreen() }
//        composable("home/detail") { HomeDetailScreen() }
//    }
//    // 个人中心模块子导航图
//    navigation(
//        startDestination = "profile/main",
//        route = MainRoute.Profile.route
//    ) {
//        composable("profile/main") { ProfileMainScreen() }
//        composable("profile/settings") { ProfileSettingsScreen() }
//    }
//}