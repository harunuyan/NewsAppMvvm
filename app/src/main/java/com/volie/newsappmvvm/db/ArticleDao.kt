package com.volie.newsappmvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.volie.newsappmvvm.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: Article)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}