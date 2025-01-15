package com.oym.binbuddy.data.repository

import com.oym.binbuddy.data.remote.TipsApi
import com.oym.binbuddy.data.remote.model.Tip
import retrofit2.Call
import retrofit2.Retrofit

class TipsRepository(
    retrofit: Retrofit
) {
    private val api = retrofit.create(TipsApi::class.java)

    fun getTips(): Call<List<Tip>> = api.getTips()

    fun addTip(newTip: Tip): Call<Tip> = api.addTip(newTip)
}