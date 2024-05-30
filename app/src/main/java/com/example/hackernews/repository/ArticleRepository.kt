package com.example.hackernews.repository

import com.example.hackernews.database.ArticleDao
import com.example.hackernews.model.Article
import com.example.hackernews.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val apiService: ApiService,
    private val articleDao: ArticleDao
) {
    fun getArticles(): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getArticles()
            if (response.isSuccessful) {
                response.body()?.let { articleResponse ->
                    articleDao.insertAll(articleResponse.hits)
                }
            }
            emit(Resource.Success(articleDao.getAllArticles().first()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An error occurred"))
        }
    }.catch { e ->
        emit(Resource.Error(e.message ?: "An error occurred"))
    }

    suspend fun deleteArticle(article: Article) {
        articleDao.deleteArticle(article)
    }
}