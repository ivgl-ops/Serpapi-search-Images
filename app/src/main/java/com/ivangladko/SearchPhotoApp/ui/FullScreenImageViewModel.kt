package com.ivangladko.SearchPhotoApp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ivangladko.SearchPhotoApp.R

class FullScreenImageViewModel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)
        val btnBack = findViewById<ImageView>(R.id.btn_back)
        val googleUrl = intent.getStringExtra("googlePhoto")
        val googleImage = findViewById<ImageView>(R.id.googleImage)
        Glide.with(this).load(googleUrl).into(googleImage)

        btnBack.setOnClickListener {
            finish()
        }
    }
}