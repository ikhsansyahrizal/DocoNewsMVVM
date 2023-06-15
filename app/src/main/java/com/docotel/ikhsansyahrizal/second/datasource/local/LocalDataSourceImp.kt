package com.docotel.ikhsansyahrizal.second.datasource.local

import android.content.SharedPreferences
import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(private val sharedPreferences: SharedPreferences) : LocalDataSource{


    private val bookmarkKey = "bookmarks"
    private val gson = Gson()

    override fun getBookmarkedArticles(): List<ArticlesItem>? {
        val articlesStringSet = sharedPreferences.getString(bookmarkKey, "")

        val listType = object : TypeToken<List<ArticlesItem>>() {}.type

        return if (articlesStringSet!="") gson.fromJson(articlesStringSet, listType) else emptyList()
    }


    override fun saveBookmarkedArticles(bookmarkedArticles: ArrayList<ArticlesItem>) {
        val listStr = Gson().toJson(bookmarkedArticles)
        sharedPreferences.edit().putString(bookmarkKey, listStr).apply()
    }

    override fun addBookmark(article: ArticlesItem) {
        val bookmarkedArticles: ArrayList<ArticlesItem> = ArrayList()
        val list = getBookmarkedArticles()

        if (list!=null){
            bookmarkedArticles.addAll(getBookmarkedArticles()!!)
        }
        bookmarkedArticles.add(0, article)
        saveBookmarkedArticles(bookmarkedArticles)
    }

    override fun removeBookmark(article: ArticlesItem) {
        val bookmarkedArticles = getBookmarkedArticles()?.toMutableList()
        bookmarkedArticles?.remove(article)
        val arrayList: ArrayList<ArticlesItem> = ArrayList()
        arrayList.addAll(bookmarkedArticles!!)
        saveBookmarkedArticles(arrayList)
    }

    override fun clearAllBookmarks() {
        sharedPreferences.edit().remove(bookmarkKey).apply()
    }

}