package com.riane.feature_home.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.riane.domian_home.repository.HomeRepository
import com.riane.feature_home.RecommendViewState
import com.riane.common.result.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    val articleList = homeRepository.getIndexList().cachedIn(viewModelScope)
    var viewStates by mutableStateOf(RecommendViewState())
        private set

    init {
        dispatch(RecommendViewAction.FetchData)
    }


    fun dispatch(action: RecommendViewAction) {
        when (action) {
            is RecommendViewAction.FetchData -> fetchData()
            is RecommendViewAction.Refresh -> refresh()
        }
    }

    private fun fetchData() {
//        ResultHandler.handleResult(
//            scope = viewModelScope,
//            flow = homeRepository.getBanner(),
//            onSuccess = {data ->
//                viewStates.copy(imageList = data)
//            }
//        )

        viewModelScope.launch {
            var imageListFlow = homeRepository.getBanner()
            var topListFlow = homeRepository.getTopArticle()

            topListFlow.zip(imageListFlow) { articleResult, bannerResult ->
                // 处理两个 Result 的组合逻辑
                when {
                    articleResult.isSuccess && bannerResult.isSuccess -> {
                        viewStates.copy(
                            imageList = bannerResult.getOrNull() ?: mutableListOf(),
                            topArticles = articleResult.getOrNull() ?: mutableListOf(),
                            isRefreshing = false
                        )
                    }

                    articleResult.isFailure -> articleResult.map {
                        viewStates.copy(topArticles = it, imageList = mutableListOf(), isRefreshing = false)
                    }

                    bannerResult.isFailure -> bannerResult.map {
                        viewStates.copy(topArticles = mutableListOf(), imageList = it, isRefreshing = false)
                    }

                    else -> {

                    }
                }
            }.onStart {
                viewStates = viewStates.copy(isRefreshing = true)
            }.catch {
                viewStates = viewStates.copy(isRefreshing = false)
            }.collect{}
        }
    }

    private fun refresh() {
        fetchData()
    }
}


sealed class RecommendViewAction {
    object FetchData : RecommendViewAction()
    object Refresh : RecommendViewAction()
}