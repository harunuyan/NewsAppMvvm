package com.volie.newsappmvvm.helper

import android.content.Context
import com.volie.newsappmvvm.db.ArticleDatabase
import com.volie.newsappmvvm.repository.NewsRepository
import com.volie.newsappmvvm.service.RetrofitInstance

class DataLayerHelper(context: Context) {

    private val database = ArticleDatabase.getInstance(context)
    private val articleDao = database.getArticleDao()

    private val newsApi = RetrofitInstance().api

    val repository = NewsRepository(newsApi,articleDao)
}