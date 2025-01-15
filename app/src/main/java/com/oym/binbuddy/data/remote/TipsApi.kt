package com.oym.binbuddy.data.remote

import com.oym.binbuddy.data.remote.model.Tip
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TipsApi {
    @GET("tips")
    fun getTips(): Call<List<Tip>>

    @POST("tips")
    fun addTip(@Body newTip: Tip): Call<Tip>
}