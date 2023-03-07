package com.volie.newsappmvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.volie.newsappmvvm.models.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)

@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null
        fun getInstance(context: Context): ArticleDatabase {
            synchronized(this) {
                if (INSTANCE != null) {
                    return INSTANCE!!
                }
                val instance = Room.databaseBuilder(
                    context,
                    ArticleDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
            }
            return INSTANCE!!
        }
    }
}