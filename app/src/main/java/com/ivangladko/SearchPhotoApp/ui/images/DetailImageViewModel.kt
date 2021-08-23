package com.ivangladko.SearchPhotoApp.ui.images

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ivangladko.SearchPhotoApp.R

class DetailImageViewModel : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail_google_image)
        val btnBack = findViewById<ImageView>(R.id.btn_back)
        val btnDetail = findViewById<Button>(R.id.button_detail)

        val googleUrl = intent.getStringExtra("googlePhoto")
        val googletitle = intent.getStringExtra("googleTitle")
        val googleSource = intent.getStringExtra("googleSource")

        val googleImage = findViewById<ImageView>(R.id.detail_googleImage)
        val title = findViewById<TextView>(R.id.textView_detail_title)
        val source = findViewById<TextView>(R.id.textView_detail_source)

        Glide.with(this).load(googleUrl).into(googleImage)
        title.text = googletitle
        source.text = googleSource

        btnDetail.setOnClickListener {
            wbViewSetup()
        }

        btnBack.setOnClickListener {
            finish()
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun wbViewSetup(){
        setContentView(R.layout.activity_web_view_detail)
        val progress_bar = findViewById<ProgressBar>(R.id.wb_detail_progress_bar)
        val googleUrl = intent.getStringExtra("targetUrl")
        val wb_view = findViewById<WebView>(R.id.wb_view_detail)
        val btnBack = findViewById<ImageView>(R.id.btn_back)
        wb_view.webViewClient = WebViewClient()
        wb_view.apply {
            progress_bar.setVisibility(View.INVISIBLE)
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