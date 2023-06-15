package com.docotel.ikhsansyahrizal.second.data.repository.local

import android.content.SharedPreferences
import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem
import com.docotel.ikhsansyahrizal.second.datasource.local.LocalDataSource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class LocalRepositoryImp @Inject constructor(private val localDataSource: LocalDataSource) : LocalRepository {


    override fun getBookmarkedArticles(): List<ArticlesItem>? {

        return localDataSource.getBookmarkedArticles()
    }


    override fun saveBookmarkedArticles(bookmarkedArticles: ArrayList<ArticlesItem>) {
        return localDataSource.saveBookmarkedArticles(bookmarkedArticles)
    }

    override fun addBookmark(article: ArticlesItem) {
        return localDataSource.addBookmark(article)
    }

    override fun removeBookmark(article: ArticlesItem) {
        return localDataSource.removeBookmark(article)
    }

    override fun clearAllBookmarks() {
        return localDataSource.clearAllBookmarks()
    }


}