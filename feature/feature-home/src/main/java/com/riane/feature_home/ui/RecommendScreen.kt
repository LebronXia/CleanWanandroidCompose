package com.riane.feature_home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.riane.feature_home.viewModel.RecommendViewAction
import com.riane.feature_home.viewModel.RecommendViewModel
import com.riane.ui.widget.CommonRefresh
import com.riane.ui.widget.LazyColumnWithPaging
import com.riane.ui.widget.rememberCommonRefreshState
import com.riane.utils.log.LogUtils

@Composable
fun RecommendScreen(
    navCtrl: NavHostController,
    viewModel: RecommendViewModel = hiltViewModel()
){

    val refreshState = rememberCommonRefreshState()
    val columnListState = rememberLazyListState()
    val recommendPagingItems =viewModel.articleList.collectAsLazyPagingItems()
    val isRefreshing = viewModel.viewStates.isRefreshing
    val banners = viewModel.viewStates.imageList
    val topArticles = viewModel.viewStates.topArticles
    val stopRefresh = remember {
        derivedStateOf {  (recommendPagingItems.loadState.refresh is LoadState.Loading).not() && isRefreshing.not()}
    }
    LaunchedEffect(recommendPagingItems.loadState.refresh){
        LogUtils.d("loadState.refresh:" + recommendPagingItems.loadState.refresh.toString())
        when {
            recommendPagingItems.loadState.refresh is LoadState.NotLoading -> {
                refreshState.isRefreshing = false
            }

            recommendPagingItems.loadState.refresh is LoadState.Error -> {
                refreshState.isRefreshing = false
            }
        }
    }

    LaunchedEffect(isRefreshing){
        LogUtils.d("viewModel.viewStates.isRefreshing:" + isRefreshing)

    }

    LaunchedEffect(stopRefresh) {

        LogUtils.d("stopRefresh:" + stopRefresh.value)
        if (stopRefresh.value) {
            refreshState.isRefreshing = false
        }
    }

    CommonRefresh(modifier = Modifier.fillMaxSize(),
         state = refreshState, onRefresh = {
             viewModel.dispatch(RecommendViewAction.Refresh)
             recommendPagingItems.refresh()
        }) {

        Column {

            LazyColumn {
//                if (banners.isNotEmpty()) {
//                    items(banners.size) {
//                        Banner()
//                    }
//                }



            }





            LazyColumnWithPaging(
                lazyPagingItems = recommendPagingItems,
                state = columnListState,
                contentPadding = PaddingValues(4.dp),
                modifier = Modifier.fillMaxSize(),
            ) {articleBean, index ->

                MultiStateItemView(data = articleBean, onSelected = {

                })
            }

        }


    }






}