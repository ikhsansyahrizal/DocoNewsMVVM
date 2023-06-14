package com.docotel.ikhsansyahrizal.second.data.repository

import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem
import com.docotel.ikhsansyahrizal.second.data.api.response.Response
import com.docotel.ikhsansyahrizal.second.data.api.retrofit.ApiService
import com.docotel.ikhsansyahrizal.second.data.api.retrofit.NewsApiService

class NewsRepository(private val apiService: NewsApiService) {

    suspend fun getTopHeadline(
        country: String,
        query: String,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): List<ArticlesItem?> {
        val response = apiService.getTopHeadlines(country, query, page, pageSize, apiKey)
        if (response.isSuccessful) {
            return response.body()?.articles ?: emptyList()
        } else {
            throw Exception(response.message())
        }


    }
}