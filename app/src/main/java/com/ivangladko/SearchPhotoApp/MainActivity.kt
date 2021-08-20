package com.ivangladko.SearchPhotoApp

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivangladko.SearchPhotoApp.*
import com.ivangladko.SearchPhotoApp.api.SerpapiApi
import com.ivangladko.SearchPhotoApp.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<ImageView>(R.id.button_toolbar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val textField = findViewById<EditText>(R.id.toolbar_field)
        val userMessage = findViewById<TextView>(R.id.user_message)
        val textInField = textField.text


        fun CreateUserResponce(textInField: String){
            val request = ServiceBuilder.buildService(SerpapiApi::class.java)
            val call = request.getImages(
                q = textInField.toString(), tmb = "isch",
                api_key = "a886ad1149ef5bc9c125857634bf405c1f6d198d40c5a9ebbd1a24d736ea37a6"
            )
            call.enqueue(object : Callback<SerpapiData> {
                override fun onResponse(call: Call<SerpapiData>, response: Response<SerpapiData>) {
                    if (response.isSuccessful) {
                        recyclerView?.apply {
                            setHasFixedSize(true)
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = PhotoAdapter(response.body()!!.images_results)
                        }
                    }
                }

                override fun onFailure(call: Call<SerpapiData>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })

        }

        btn.setOnClickListener {
            userMessage.setVisibility(View.GONE);
            CreateUserResponce(textInField.toString())
        }
    }
}

