package com.docotel.ikhsansyahrizal.second.datasource.remote

import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem

interface RemoteDataSource {

    suspend fun getNews(country: String, query: String, page: Int, pageSize: Int, apiKey: String) : List<ArticlesItem>

}