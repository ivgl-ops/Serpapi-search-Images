package com.ivangladko.SearchPhotoApp.api

import com.ivangladko.SearchPhotoApp.SerpapiData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SerpapiApi {

    @GET("search.json")
    fun getMovies(
        @Query("q") q: String,
        @Query("tbm") tmb: String,
        @Query("api_key") api_key: String
    ): Call<SerpapiData>

}