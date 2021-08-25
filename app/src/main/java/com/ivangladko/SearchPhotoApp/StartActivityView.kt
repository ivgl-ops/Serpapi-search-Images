package com.ivangladko.SearchPhotoApp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import com.ivangladko.SearchPhotoApp.ui.images.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartActivityView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val editor: SharedPreferences.Editor = getSharedPreferences("clear_cache", MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
        var request: String
        val btn = findViewById<ImageView>(R.id.startbtn)
        val startRequest = findViewById<EditText>(R.id.startRequest)

        btn.setOnClickListener {
            request = startRequest.text.toString()
            val i = Intent(this@StartActivityView, MainActivityViewModel::class.java)
            i.putExtra("UserRequest", request)
            startActivity(i)
        }
    }
}