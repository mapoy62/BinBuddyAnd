package com.oym.binbuddy.data.remote

import com.oym.binbuddy.data.remote.model.RecyclingCategory
import retrofit2.Call
import retrofit2.http.GET

interface RecyclingApi {

    @GET("/explore/recycling_points")
    fun getRecyclingCategories(): Call<List<RecyclingCategory>>
}

