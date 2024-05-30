package com.example.hackernews.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("hits") val hits: List<Article>
)