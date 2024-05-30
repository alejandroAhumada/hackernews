package com.example.hackernews.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.hackernews.model.Article
import com.example.hackernews.repository.Resource
import com.example.hackernews.viewmodel.ArticleViewModel

@Composable
fun ArticlesScreen(navController: NavController, viewModel: ArticleViewModel) {
    val articles by viewModel.articles.collectAsState()

    when (articles) {
        is Resource.Loading -> CircularProgressIndicator()
        is Resource.Success -> {
            val articleList = (articles as Resource.Success<List<Article>>).data
            LazyColumn {
                if (articleList != null) {
                    items(articleList.size) { article ->
                        val news = articleList[article]
                        ArticleItem(news, onClick = {
                            navController.navigate("webview/${news.url}")
                        }, onDelete = {
                            viewModel.deleteArticle(news)
                        })
                    }
                }
            }
        }
        is Resource.Error -> {
            (articles as Resource.Error).message?.let { Text(text = it) }
        }
    }
}