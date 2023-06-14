package com.docotel.ikhsansyahrizal.second.data.repository.remote

import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem

interface RemoteRepositoryInterface {

    suspend fun getNews(country: String, query: String, page: Int, pageSize: Int, apiKey: String) : List<ArticlesItem?>
}