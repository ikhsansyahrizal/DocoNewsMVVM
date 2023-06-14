package com.docotel.ikhsansyahrizal.second.ui.main.features.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.docotel.ikhsansyahrizal.second.helper.StateApi
import com.docotel.ikhsansyahrizal.second.data.api.response.ArticlesItem
import com.docotel.ikhsansyahrizal.second.data.repository.remote.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (private val remoteRepository: RemoteRepository) : ViewModel() {

    private val _stateApi : MutableLiveData<StateApi<List<ArticlesItem>>> = MutableLiveData()
    val stateApi: LiveData<StateApi<List<ArticlesItem>>> = _stateApi

    fun fetchNews(country : String, query : String, page: Int, pageSize: Int, apiKey: String) {
        viewModelScope.launch {
            try {
                _stateApi.value = StateApi.Loading
                val articles = remoteRepository.getNews(country, query, page, pageSize, apiKey)
                _stateApi.value = StateApi.Success(articles)
            } catch (e: Exception){
                _stateApi.value = StateApi.NotLoading
                Log.d("failed fetch data", e.toString())
            }
            _stateApi.value = StateApi.NotLoading
        }
    }
}