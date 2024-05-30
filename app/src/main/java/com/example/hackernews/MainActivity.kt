package com.example.hackernews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hackernews.ui.ArticlesScreen
import com.example.hackernews.ui.WebViewScreen
import com.example.hackernews.ui.theme.HackernewsTheme
import com.example.hackernews.viewmodel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val articleViewModel: ArticleViewModel by viewModels()
        super.onCreate(savedInstanceState)
        setContent {
            HackernewsTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "articles") {
                        composable("articles") { ArticlesScreen(navController, articleViewModel) }
                        composable("webview/{url}") { backStackEntry ->
                            val url = backStackEntry.arguments?.getString("url")
                            url?.let { WebViewScreen(navController, it) }
                        }
                    }
                }
            }
        }
    }
}