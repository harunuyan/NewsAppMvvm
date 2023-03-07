package com.volie.newsappmvvm.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.newsappmvvm.helper.DataLayerHelper
import com.volie.newsappmvvm.models.Article
import kotlinx.coroutines.launch

class SearchNewsViewModel(context: Context) : ViewModel() {

    var mDataLayerHelper = DataLayerHelper(context)
    val mSearchNews = MutableLiveData<List<Article>>()
    val mLoadingData = MutableLiveData<Boolean>()
    var mSearchNewsPage = 1

    private suspend fun searchNewsData(searchQuery: String, pageNumber: Int): List<Article> {
        return mDataLayerHelper.repository.searchNews(searchQuery, pageNumber)
    }

    fun searchNews(searchQuery: String) {
        mLoadingData.postValue(false)
        viewModelScope.launch {
            val searchData = searchNewsData(searchQuery, mSearchNewsPage)
            if (searchData.isEmpty()) {
                mLoadingData.postValue(true)
            } else {
                mSearchNews.postValue(searchData)
            }
        }
    }
}