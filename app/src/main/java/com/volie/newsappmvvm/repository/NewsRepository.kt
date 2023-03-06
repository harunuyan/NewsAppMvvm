package com.volie.newsappmvvm.repository

import com.volie.newsappmvvm.db.ArticleDao
import com.volie.newsappmvvm.service.NewsAPI

class NewsRepository(
    private val newsApi: NewsAPI,
    private val articleDao: ArticleDao
) {

}