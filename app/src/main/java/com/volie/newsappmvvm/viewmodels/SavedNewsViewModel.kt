package com.volie.newsappmvvm.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volie.newsappmvvm.helper.DataLayerHelper
import com.volie.newsappmvvm.models.Article
import kotlinx.coroutines.launch

class SavedNewsViewModel(context: Context) : ViewModel() {

    private val mDataLayerHelper = DataLayerHelper(context)


    fun saveUndoArticle(article: Article) = viewModelScope.launch {
        mDataLayerHelper.repository.saveArticle(article)
    }

    fun getSavedNews() = mDataLayerHelper.repository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        mDataLayerHelper.repository.deleteArticle(article)
    }
}