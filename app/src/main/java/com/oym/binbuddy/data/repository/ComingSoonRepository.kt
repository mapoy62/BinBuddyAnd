package com.oym.binbuddy.data.repository

import com.oym.binbuddy.data.remote.ComingSoonApi
import com.oym.binbuddy.data.remote.model.ComingSoonItem

import retrofit2.Call
import retrofit2.Retrofit


class ComingSoonRepository(
    private val retrofit: Retrofit
) {
    private val api:ComingSoonApi = retrofit.create(ComingSoonApi::class.java)

    fun getComingSoonItems(): Call<List<ComingSoonItem>> = api.getComingSoonItems()
}

