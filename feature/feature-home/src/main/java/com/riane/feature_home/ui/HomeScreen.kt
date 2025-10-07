package com.riane.feature_home.ui

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.riane.feature_home.navigation.SearchRoutes
import com.riane.feature_home.viewModel.HomeViewModel
import com.riane.ui.component.HomeSearchBar
import com.riane.ui.component.TextTabBar
import com.riane.ui.widget.SampleAlertDialog
import com.riane.utils.log.LogUtils
import kotlinx.coroutines.launch
import kotlin.math.truncate

@Composable
fun HomeScreen(
    navCtrl: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val titles = viewModel.viewStates.titles
    val scopeState = rememberCoroutineScope()
    Column {

        val pagerState = rememberPagerState(initialPage = 0) {
            titles.size
        }

        LogUtils.d("titles size is:" + titles.size)

        TextTabBar(
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
            index = pagerState.currentPage,
            tabTexts = titles,
            onTabSelected = { index ->
                scopeState.launch {
                    pagerState.scrollToPage(index)
                }
            }
        )


        HomeSearchBar(
            onSearchClick = {
                // RouteUtils.navTo(navCtrl, RouteName.ARTICLE_SEARCH + "/111")
                //viewModel.setShowSettingsDialog(true)

                navCtrl.navigate(
                    "${SearchRoutes.SEARCH}/${
                        "哈哈哈哈"
                    }"
                )
            }
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 50.dp)
        ) { page ->
            when(page){
                0 -> RecommendScreen(navCtrl)
                1 -> RecommendScreen(navCtrl)
                2 -> RecommendScreen(navCtrl)
            }
        }

    }


    if (viewModel.shouldShowSettingsDialog) {
        SampleAlertDialog(
            title = "提示",
            content = "退出后，将无法查看我的文章、消息、收藏、积分、浏览记录等功能，确定退出登录吗？",
            onConfirmClick = {

            },
            onDismiss = {
                viewModel.setShowSettingsDialog(false)
            }
        )
    }

//    Box(
//
//        modifier = Modifier
//            .fillMaxSize()
//            .systemBarsPadding()
//    ) {
//
//        Text("首页")
//
//
//    }
}
