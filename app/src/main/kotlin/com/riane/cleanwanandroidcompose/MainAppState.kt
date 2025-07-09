package com.riane.cleanwanandroidcompose

import androidx.annotation.DrawableRes
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.riane.feature_home.navigation.navigateToHome

@Composable
fun rememberMainState(
    navController: NavHostController = rememberNavController(),
): MainAppState {
    return remember(navController) {
        MainAppState(
            navController = navController,
        )
    }
}

class MainAppState(
    val navController: NavHostController,
) {

    val mainTopLevelDestinations: List<BottomNavigationItem> = BottomNavigationItem.entries

    fun navigateToMainTopLevelDestination(topLevelDestination: BottomNavigationItem, changeStatusBarIconMode: (Boolean) -> Unit) {

        when (topLevelDestination) {
            BottomNavigationItem.Home -> {
                navController.navigateToHome()
                changeStatusBarIconMode(false)
            }

            BottomNavigationItem.HotSpot -> {
                navController.navigateToHome()
                changeStatusBarIconMode(false)
            }

            BottomNavigationItem.System -> {
                navController.navigateToHome()
                changeStatusBarIconMode(false)
            }

            BottomNavigationItem.Profile -> {
                navController.navigateToHome()
                changeStatusBarIconMode(false)
            }

        }
    }


}

public enum class BottomNavigationItem(
    @DrawableRes val actionIcon: Int,
    @DrawableRes val unActiveIcon: Int,
    val title: String,

){
    Home(actionIcon = R.drawable.ic_tab_home_active,
        unActiveIcon = R.drawable.ic_tab_home_unactive,
        title = "首页",
    ),
    HotSpot(actionIcon = R.drawable.ic_tab_likes_active,
        unActiveIcon = R.drawable.ic_tab_likes_unactive,
        title = "热点",
    ),
    System(actionIcon = R.drawable.ic_tab_chats_active,
        unActiveIcon = R.drawable.ic_tab_chats_unactive,
        title = "体系",
    ),
    Profile(actionIcon = R.drawable.ic_tab_profile_active,
        unActiveIcon = R.drawable.ic_tab_profile_unactive,
        title = "我的",
    ),

}