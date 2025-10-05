package com.riane.domian_home.repository

import androidx.paging.PagingData
import com.riane.domian_home.model.ArticleBean
import com.riane.domian_home.model.BannerBean
import com.riane.domian_home.model.ListWrapper
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getIndexList(): Flow<PagingData<ArticleBean>>

    fun getTopArticle() : Flow<Result<MutableList<ArticleBean>>>

    fun getBanner(): Flow<Result<MutableList<BannerBean>>>
}