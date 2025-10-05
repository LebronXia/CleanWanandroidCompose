package com.riane.feature_home

import androidx.compose.foundation.lazy.LazyListState
import com.riane.domian_home.model.ArticleBean
import com.riane.domian_home.model.BannerBean

data class RecommendViewState(
    val isRefreshing: Boolean = false,
    val imageList: List<BannerBean> = emptyList(),
    val topArticles: List<ArticleBean> = emptyList(),
    val listState: LazyListState = LazyListState()
)