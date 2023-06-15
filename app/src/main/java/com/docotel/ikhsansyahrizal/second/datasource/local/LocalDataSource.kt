package com.docotel.ikhsansyahrizal.second.datasource.local

import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem

interface LocalDataSource {

    fun getBookmarkedArticles() : List<ArticlesItem>?

    fun saveBookmarkedArticles(bookmarkedArticles: ArrayList<ArticlesItem>)

    fun addBookmark(article: ArticlesItem)

    fun removeBookmark(article: ArticlesItem)

    fun clearAllBookmarks()

}