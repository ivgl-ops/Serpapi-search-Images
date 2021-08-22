package com.ivangladko.SearchPhotoApp

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView

class StartActivityView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        var request: String = ""
        val btn = findViewById<ImageView>(R.id.startbtn)
        val startRequest = findViewById<EditText>(R.id.startRequest)
        val textField = findViewById<EditText>(R.id.toolbar_field)
        btn.setOnClickListener {
            request = startRequest.text.toString()
            val i = Intent(this@StartActivityView, MainActivity::class.java)
            i.putExtra("UserRequest", request)
            startActivity(i)
        }

    }
}