package com.ivangladko.SearchPhotoApp.ui.images

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivangladko.SearchPhotoApp.*
import com.ivangladko.SearchPhotoApp.api.SerpapiApi
import com.ivangladko.SearchPhotoApp.api.ServiceBuilder
import com.ivangladko.SearchPhotoApp.utils.Constants.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivityViewModel : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userrequest = intent.getStringExtra("UserRequest")
        val btn = findViewById<ImageView>(R.id.button_toolbar)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val textField = findViewById<EditText>(R.id.toolbar_field)
        val progressbar = findViewById<ProgressBar>(R.id.progress_bar)
        val textInField = textField.text
        textField.setHint(userrequest)

        fun CreateUserResponce(textInField: String){
            val request = ServiceBuilder.buildService(SerpapiApi::class.java)
            val call = request.getImages(
                q = textInField.toString(), tmb = "isch",
                api_key = API_KEY
            )
            call.enqueue(object : Callback<SerpapiData> {
                override fun onResponse(call: Call<SerpapiData>, response: Response<SerpapiData>) {
                    if (response.isSuccessful) {
                        recyclerView?.apply {
                            progressbar.setVisibility(View.INVISIBLE);
                            setHasFixedSize(true)
                            layoutManager = LinearLayoutManager(this@MainActivityViewModel)
                            adapter = PhotoAdapter(response.body()!!.images_results)
                        }
                    }
                }

                override fun onFailure(call: Call<SerpapiData>, t: Throwable) {
                    Toast.makeText(this@MainActivityViewModel, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })

        }

        CreateUserResponce(userrequest.toString())
        btn.setOnClickListener {
            textField.setHint(textInField.toString())
            CreateUserResponce(textInField.toString())
        }

    }
}

