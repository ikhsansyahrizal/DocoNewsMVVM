package com.docotel.ikhsansyahrizal.second.datasource.remote

import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem
import com.docotel.ikhsansyahrizal.second.data.api.retrofit.NewsApiService
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val newsApiService: NewsApiService) : RemoteDataSource {
    override suspend fun getNews(
        country: String,
        query: String,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): List<ArticlesItem> {
        val response = if (query.isEmpty()) {
            newsApiService.getTopHeadlines(country, query, page, pageSize, apiKey)
        } else {
            newsApiService.searchNews(query, page, pageSize, apiKey)
        }

        if (response.isSuccessful) {
            return response.body()?.articles ?: emptyList()
        } else {
            throw Exception(response.message())
        }
    }
}