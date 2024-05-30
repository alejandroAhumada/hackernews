package com.example.hackernews.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey @SerializedName("objectID") val id: String,
    @SerializedName("title") val title: String?,
    @SerializedName("author") val author: String?,
    @SerializedName("created_at") val createdAt: String?,
    @SerializedName("url") val url: String?
)