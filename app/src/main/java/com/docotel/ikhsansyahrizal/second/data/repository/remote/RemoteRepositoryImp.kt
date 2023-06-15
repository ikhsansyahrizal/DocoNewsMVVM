package com.docotel.ikhsansyahrizal.second.data.repository.remote

import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem
import com.docotel.ikhsansyahrizal.second.datasource.remote.RemoteDataSource
import javax.inject.Inject

class RemoteRepositoryImp @Inject constructor(private val remoteDataSource: RemoteDataSource) : RemoteRepository {

    override suspend fun getNews(
        country: String,
        query: String,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): List<ArticlesItem> {

        return remoteDataSource.getNews(country, query, page, pageSize, apiKey)

    }


}