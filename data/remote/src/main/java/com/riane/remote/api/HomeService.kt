package com.riane.remote.api

import com.riane.domian_home.model.ArticleBean
import com.riane.domian_home.model.BannerBean
import com.riane.domian_home.model.ListWrapper
import com.riane.network.model.BaseResponse
import com.riane.remote.model.LoginDto
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {

    //首页
    @GET("/article/list/{page}/json")
    suspend fun getIndexList(@Path("page") page: Int): BaseResponse<ListWrapper<ArticleBean>>

    //置顶文正
    @GET("/article/top/json")
    suspend fun getTopArticle(): BaseResponse<MutableList<ArticleBean>>

    //banner
    @GET("/banner/json")
    suspend fun getBanners(): BaseResponse<MutableList<BannerBean>>
}