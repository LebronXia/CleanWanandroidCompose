package com.riane.remote.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.riane.domian_home.model.ArticleBean
import com.riane.remote.api.HomeService
import javax.inject.Inject

class HomeArticleDataSource @Inject constructor(
    private val homeService: HomeService
) : PagingSource<Int, ArticleBean>(){
    override fun getRefreshKey(state: PagingState<Int, ArticleBean>): Int? {
        //// 根据preKey和nextKey中找到离anchorPosition最近页面的键值
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleBean> {
        // 定义键值
        val currentKey = params.key ?: 0
        return try {
            val response = homeService.getIndexList(currentKey)
            val datas = response.data.datas
            LoadResult.Page(
                data = datas,
                prevKey = if (currentKey == 0) null else currentKey - 1,
                nextKey = if (currentKey == response.data.pageCount) null else currentKey + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }

    }

}