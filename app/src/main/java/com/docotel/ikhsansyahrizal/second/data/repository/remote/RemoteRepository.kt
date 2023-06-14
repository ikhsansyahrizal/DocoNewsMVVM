package com.docotel.ikhsansyahrizal.second.data.repository.remote

import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem
import com.docotel.ikhsansyahrizal.second.data.api.retrofit.NewsApiService
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val apiService: NewsApiService) : RemoteRepositoryInterface {

    override suspend fun getNews(
        country: String,
        query: String,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): List<ArticlesItem> {
        val response = if (query.isEmpty()) {
            apiService.getTopHeadlines(country, query, page, pageSize, apiKey)
        } else {
            apiService.searchNews(query, page, pageSize, apiKey)
        }

        if (response.isSuccessful) {
            return response.body()?.articles ?: emptyList()
        } else {
            throw Exception(response.message())
        }


    }
}