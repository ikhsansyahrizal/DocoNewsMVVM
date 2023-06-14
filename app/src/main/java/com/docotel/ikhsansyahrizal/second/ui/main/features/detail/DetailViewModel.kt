package com.docotel.ikhsansyahrizal.second.ui.main.features.detail

import androidx.lifecycle.ViewModel
import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem
import com.docotel.ikhsansyahrizal.second.data.repository.local.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val localRepository: LocalRepository): ViewModel() {

    fun addBookmark(article: ArticlesItem) {
        localRepository.addBookmark(article)
    }

    fun removeBookmark(article: ArticlesItem) {
        localRepository.removeBookmark(article)
    }

    fun getBookmarkedArticles(): List<ArticlesItem> {
        return localRepository.getBookmarkedArticles()!!
    }

}
