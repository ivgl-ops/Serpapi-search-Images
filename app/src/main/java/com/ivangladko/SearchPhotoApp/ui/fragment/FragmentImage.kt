package com.ivangladko.SearchPhotoApp.ui.fragment

import android.os.Bundle
import androidx.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.ivangladko.SearchPhotoApp.PhotoAdapter
import com.ivangladko.SearchPhotoApp.R
import com.ivangladko.SearchPhotoApp.SerpapiData
import com.ivangladko.SearchPhotoApp.api.SerpapiApi
import com.ivangladko.SearchPhotoApp.api.ServiceBuilder
import com.ivangladko.SearchPhotoApp.ui.images.MainActivityViewModel
import com.ivangladko.SearchPhotoApp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentImage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_image)
        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)
        val imageList = ArrayList<SlideModel>()

        val requestUser = PreferenceManager.getDefaultSharedPreferences(this).getString("UserRequestPhoto", null)
        val nextRequestUser = PreferenceManager.getDefaultSharedPreferences(this).getString("textInField", null)


        val thisPosition = intent.getIntExtra("ThisPositionImage", 0)
        fun CreateUserResponce(textInField: String){
            val request = ServiceBuilder.buildService(SerpapiApi::class.java)
            val call = request.getImages(
                q = textInField.toString(), tmb = "isch",
                api_key = Constants.API_KEY
            )

            call.enqueue(object : Callback<SerpapiData> {
                override fun onResponse(call: Call<SerpapiData>, response: Response<SerpapiData>) {
                    if (response.isSuccessful) {
                        val photo_list = PhotoAdapter(response.body()!!.images_results).getImage()
                        photo_list.subList(0, thisPosition - 1).clear()
                        for (i in photo_list ){
                            imageList.add(SlideModel(i))
                        }
                        imageSlider.setImageList(imageList, ScaleTypes.CENTER_INSIDE)
                    }
                }

                override fun onFailure(call: Call<SerpapiData>, t: Throwable) {
                    Toast.makeText(this@FragmentImage, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })

        }

            if (nextRequestUser == null){
                CreateUserResponce(requestUser.toString())

            } else{
                CreateUserResponce(nextRequestUser.toString())
            }

    }
}