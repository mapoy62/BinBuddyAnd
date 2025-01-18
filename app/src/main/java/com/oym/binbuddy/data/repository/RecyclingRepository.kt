package com.oym.binbuddy.data.repository

import com.oym.binbuddy.data.remote.RecyclingApi
import com.oym.binbuddy.data.remote.model.RecyclingCategory
import retrofit2.Call
import retrofit2.Retrofit

class RecyclingRepository(
    retrofit: Retrofit
) {
    private val api = retrofit.create(RecyclingApi::class.java)

    fun getRecyclingCategories(): Call<List<RecyclingCategory>> {
        return api.getRecyclingCategories()
    }
}
