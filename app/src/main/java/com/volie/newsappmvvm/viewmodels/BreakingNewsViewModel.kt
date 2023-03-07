package com.volie.newsappmvvm.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.newsappmvvm.helper.DataLayerHelper
import com.volie.newsappmvvm.models.Article
import kotlinx.coroutines.launch

class BreakingNewsViewModel(context: Context) : ViewModel() {

    val mDataLayerHelper = DataLayerHelper(context)
    val mBreakingNews = MutableLiveData<List<Article>>()
    val mLoadingData = MutableLiveData<Boolean>()
    var mBreakingNewsPage = 1


    private suspend fun getBreakingNewsFromRemote(
        countryCode: String,
        pageNumber: Int
    ): List<Article> {
        return mDataLayerHelper.repository.getBreakingNews(countryCode, pageNumber)
    }

    fun getBreakingNews(countryCode: String) {
        mLoadingData.postValue(false)
        viewModelScope.launch {
            val remoteData = getBreakingNewsFromRemote(countryCode, mBreakingNewsPage)
            if (remoteData.isEmpty()) {
                mLoadingData.postValue(true)
            } else {
                mBreakingNews.postValue(remoteData)
            }
        }
    }
}