package com.docotel.ikhsansyahrizal.second.ui.main.features.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem
import com.docotel.ikhsansyahrizal.second.data.repository.NewsRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _newsArticles = MutableLiveData<List<ArticlesItem?>>()
    val newsArticle: LiveData<List<ArticlesItem?>> = _newsArticles

    fun fetchTopHeadlines(country : String, query : String, page: Int, pageSize: Int, apiKey: String) {
        viewModelScope.launch {
            try {
                val articles = repository.getTopHeadline(country, query, page, pageSize, apiKey)
                _newsArticles.value = articles
            } catch (e: Exception){
                Log.d("failed fetch data", e.toString())
            }
        }
    }
}