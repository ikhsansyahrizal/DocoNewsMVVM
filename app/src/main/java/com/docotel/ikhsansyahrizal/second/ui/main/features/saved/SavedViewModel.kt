package com.docotel.ikhsansyahrizal.doconewss.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem
import com.docotel.ikhsansyahrizal.second.data.repository.local.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(private val localRepository: LocalRepository): ViewModel() {

    private val _bookMarkedArticles: MutableLiveData<List<ArticlesItem>> = MutableLiveData()
    val bookmarkedArticlesObserve: LiveData<List<ArticlesItem>> get() = _bookMarkedArticles


    init {
        _bookMarkedArticles.value = getBookmarkedArticles()
    }

    fun clearAllData() {
        localRepository.clearAllBookmarks()
    }

    fun getBookmarkedArticles(): List<ArticlesItem> {
        return localRepository.getBookmarkedArticles()!!
    }



}