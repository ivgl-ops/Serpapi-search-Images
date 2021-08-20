package com.ivangladko.SearchPhotoApp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ivangladko.SearchPhotoApp.R
import org.w3c.dom.Text

class DetailGoogleImage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail_google_image)
        val btnBack = findViewById<ImageView>(R.id.btn_back)
        val googleUrl = intent.getStringExtra("googlePhoto")
        val googletitle = intent.getStringExtra("googleTitle")
        val googleSource = intent.getStringExtra("googleUrl")

        val googleImage = findViewById<ImageView>(R.id.detail_googleImage)
        val title = findViewById<TextView>(R.id.textView_detail_title)
        val source = findViewById<TextView>(R.id.textView_detail_source)

        Glide.with(this).load(googleUrl).into(googleImage)
        title.text = googletitle
        source.text = googleSource

        println(googleUrl.toString())
        btnBack.setOnClickListener {
            finish()
        }
    }
}