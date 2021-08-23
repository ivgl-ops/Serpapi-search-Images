 package com.ivangladko.SearchPhotoApp.api

import com.ivangladko.SearchPhotoApp.SerpapiData
import com.ivangladko.SearchPhotoApp.utils.Constants.END_POINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface SerpapiApi {

    @GET(END_POINT)
    fun getImages(
        @Query("q") q: String,
        @Query("tbm") tmb: String,
        @Query("api_key") api_key: String
    ): Call<SerpapiData>

}