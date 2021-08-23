package com.ivangladko.SearchPhotoApp.ui.fragment

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ivangladko.SearchPhotoApp.R

class FragmentImage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_image)

        val googlePhotoLink = intent.getStringExtra("googlePhoto")
        val googlePhoto = findViewById<ImageView?>(R.id.google_photo)
        Glide.with(this).load(googlePhotoLink).into(googlePhoto)
        println(googlePhotoLink)

    }
}