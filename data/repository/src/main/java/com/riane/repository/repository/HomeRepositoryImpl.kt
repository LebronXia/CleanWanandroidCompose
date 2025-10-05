package com.riane.repository.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.riane.auth.repository.AuthRepository
import com.riane.domian_home.model.ArticleBean
import com.riane.domian_home.model.BannerBean
import com.riane.domian_home.model.ListWrapper
import com.riane.domian_home.repository.HomeRepository
import com.riane.remote.api.HomeService
import com.riane.remote.safeApiCallAsFlow
import com.riane.remote.source.AuthRemoteDataSource
import com.riane.remote.source.HomeArticleDataSource
import com.riane.remote.source.HomeRemoteDatSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDatSource: HomeRemoteDatSource,
    private val homeService: HomeService
) : HomeRepository {
    override fun getIndexList(): Flow<PagingData<ArticleBean>> {
// safeApiCallAsFlow { homeRemoteDatSource.getIndexList(page) }
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                HomeArticleDataSource(homeService)
            }
        ).flow

    }

    override fun getTopArticle(): Flow<Result<MutableList<ArticleBean>>> {
        return safeApiCallAsFlow { homeRemoteDatSource.getTopArticle() }
    }

    override fun getBanner(): Flow<Result<MutableList<BannerBean>>> {
        return safeApiCallAsFlow { homeRemoteDatSource.getBanner() }
    }

}