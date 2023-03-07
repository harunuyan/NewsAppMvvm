package com.volie.newsappmvvm.repository

import com.volie.newsappmvvm.db.ArticleDao
import com.volie.newsappmvvm.models.Article
import com.volie.newsappmvvm.service.NewsAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(
    private val newsApi: NewsAPI,
    private val articleDao: ArticleDao
) {

    suspend fun getNews(): List<Article> {
        return withContext(Dispatchers.IO) {
            val result = kotlin.runCatching {
                newsApi.getBreakingNews()
            }.onFailure {
                print(it)
            }.getOrNull()
            if (result?.isSuccessful == true) {
                val list = result.body()?.articles.orEmpty()
//                articleDao.insert(list)
                list
            } else {
                emptyList()
            }
        }
    }
}