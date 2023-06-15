package com.docotel.ikhsansyahrizal.second.data.repository.local

import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem

interface LocalRepository {

    fun getBookmarkedArticles() : List<ArticlesItem>?

    fun saveBookmarkedArticles(bookmarkedArticles: ArrayList<ArticlesItem>)

    fun addBookmark(article: ArticlesItem)

    fun removeBookmark(article: ArticlesItem)

    fun clearAllBookmarks()
}