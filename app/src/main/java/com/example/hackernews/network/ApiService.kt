package com.example.hackernews.network

import com.example.hackernews.model.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("search_by_date?query=mobile")
    suspend fun getArticles(): Response<ArticleResponse>
}