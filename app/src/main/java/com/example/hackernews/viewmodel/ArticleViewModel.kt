package com.example.hackernews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackernews.model.Article
import com.example.hackernews.repository.ArticleRepository
import com.example.hackernews.repository.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private val _articles = MutableStateFlow<Resource<List<Article>>>(Resource.Loading())
    val articles: StateFlow<Resource<List<Article>>> = _articles

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            repository.getArticles().collect { result ->
                _articles.value = result
            }
        }
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            repository.deleteArticle(article)
        }
    }
}