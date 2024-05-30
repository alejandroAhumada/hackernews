package com.example.hackernews.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hackernews.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles ORDER BY createdAt DESC")
    fun getAllArticles(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Article>)

    @Delete
    suspend fun deleteArticle(article: Article)

    /*@Query("DELETE FROM articles WHERE id = :id")
    suspend fun deleteArticle(id: String)*/
}