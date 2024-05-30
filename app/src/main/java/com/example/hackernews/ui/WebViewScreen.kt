package com.example.hackernews.ui

import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@Composable
fun WebViewScreen(navController: NavController, url: String) {
    val context = LocalContext.current
    AndroidView(factory = {
        WebView(context).apply {
            settings.javaScriptEnabled = true
            loadUrl(url)
        }
    }, update = { webView ->
        webView.loadUrl(url)
    })
}