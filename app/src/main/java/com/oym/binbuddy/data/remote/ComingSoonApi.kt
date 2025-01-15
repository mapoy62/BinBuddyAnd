package com.oym.binbuddy.data.remote

import com.oym.binbuddy.data.remote.model.ComingSoonItem
import retrofit2.Call
import retrofit2.http.GET

interface ComingSoonApi {
    @GET("/events/banner") // Reemplaza "coming_soon" con el endpoint de tu API en Apiary
    fun getComingSoonItems(): Call<List<ComingSoonItem>>
}