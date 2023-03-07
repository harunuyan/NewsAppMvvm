package com.volie.newsappmvvm.repository

import android.util.Log
import com.volie.newsappmvvm.db.ArticleDao
import com.volie.newsappmvvm.models.Article
import com.volie.newsappmvvm.service.NewsAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(
    private val newsApi: NewsAPI,
    private val articleDao: ArticleDao
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): List<Article> {
        return withContext(Dispatchers.IO) {
            val result = kotlin.runCatching {
                newsApi.getBreakingNews(countryCode, pageNumber)
            }.onFailure {
                Log.i("ERROR: getBreakingNews", "$it")
            }.getOrNull()
            if (result?.isSuccessful == true) {
                val list = result.body()?.articles.orEmpty()
                articleDao.insert(list)
                list
            } else {
                emptyList()
            }
        }
    }

    suspend fun searchNews(searchQuery: String, pageNumber: Int): List<Article> {
        return withContext(Dispatchers.IO) {
            val result = kotlin.runCatching {
                newsApi.searchForNews(searchQuery, pageNumber)
            }.onFailure {
                Log.i("ERROR: searchNews", "$it")
            }.getOrNull()
            if (result?.isSuccessful == true) {
                val list = result.body()?.articles.orEmpty()
                list
            } else {
                emptyList()
            }
        }
    }
}