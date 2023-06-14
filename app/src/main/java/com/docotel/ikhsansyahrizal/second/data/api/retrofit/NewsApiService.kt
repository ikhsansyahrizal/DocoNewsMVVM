package com.docotel.ikhsansyahrizal.second.data.api.retrofit

import com.docotel.ikhsansyahrizal.second.helper.Constant
import com.docotel.ikhsansyahrizal.second.data.api.response.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiService {

    @GET(Constant.TOP_HEADLINE)
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): retrofit2.Response<Response>


    @GET(Constant.EVERYTHING)
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ) : retrofit2.Response<Response>

}