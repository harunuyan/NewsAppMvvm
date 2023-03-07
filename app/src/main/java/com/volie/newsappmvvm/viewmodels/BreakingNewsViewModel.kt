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


    suspend fun getBreakingNewsFromRemote(): List<Article> {
        return mDataLayerHelper.repository.getNews()
    }

    fun getBreakingNews() {
        mLoadingData.postValue(false)
        viewModelScope.launch {
            val remoteData = getBreakingNewsFromRemote()
            if (remoteData.isEmpty()) {
                mLoadingData.postValue(true)
            } else {
                mBreakingNews.postValue(remoteData)
            }
        }
    }
}