package com.riane.remote.source

import com.riane.domian_home.model.ArticleBean
import com.riane.domian_home.model.BannerBean
import com.riane.domian_home.model.ListWrapper
import com.riane.network.model.BaseResponse
import com.riane.remote.api.HomeService
import javax.inject.Inject

class HomeRemoteDatSource @Inject constructor(
    private val homeService: HomeService
) {

    suspend fun getIndexList(page: Int): BaseResponse<ListWrapper<ArticleBean>> =
         homeService.getIndexList(page)


    suspend fun getTopArticle() : BaseResponse<MutableList<ArticleBean>>{
        return homeService.getTopArticle()
    }

    suspend fun getBanner(): BaseResponse<MutableList<BannerBean>>{
        return homeService.getBanners()
    }
}