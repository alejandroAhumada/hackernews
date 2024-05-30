package com.example.hackernews.ui

import androidx.compose.foundation.clickable
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hackernews.model.Article


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticleItem(article: Article, onClick: () -> Unit, onDelete: () -> Unit) {
    SwipeToDismiss(
        directions = setOf(
            DismissDirection.StartToEnd
        ),
        state = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToEnd) {
                onDelete()
            }
            true
        }
    ), background = {}) {
        ListItem(
             { Text(article.author ?: "No Title") },
            modifier = Modifier.clickable { onClick() }
        )
    }
}