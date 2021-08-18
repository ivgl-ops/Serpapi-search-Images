package com.ivangladko.SearchPhotoApp.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.ivangladko.SearchPhotoApp.R

class WebViewActivityViewModel : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        wbViewSetup()
    }

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun wbViewSetup(){
        val googleUrl = intent.getStringExtra("googleUrl")
        val wb_view = findViewById<WebView>(R.id.wb_view)
        val btnBack = findViewById<ImageView>(R.id.btn_back)
        wb_view.webViewClient = WebViewClient()
        wb_view.apply {
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            loadUrl(googleUrl.toString())
            println(googleUrl.toString())
        }

        btnBack.setOnClickListener {
            if(wb_view.canGoBack()) wb_view.goBack() else super.onBackPressed()
        }
    }

}