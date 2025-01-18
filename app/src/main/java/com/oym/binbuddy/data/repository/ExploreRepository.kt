package com.oym.binbuddy.data.repository

import com.oym.binbuddy.data.remote.ExploreApi
import com.oym.binbuddy.data.remote.model.InstagramProfile
import retrofit2.Call
import retrofit2.Retrofit

class ExploreRepository(
    retrofit: Retrofit
) {
    private val api = retrofit.create(ExploreApi::class.java)

    fun getInstagramProfiles(): Call<List<InstagramProfile>> {
        return api.getInstagramProfiles()
    }
}