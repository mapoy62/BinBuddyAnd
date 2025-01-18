package com.oym.binbuddy.data.remote

import com.oym.binbuddy.data.remote.model.Reward
import retrofit2.Call
import retrofit2.http.GET

interface RewardsApi {
    @GET("/rewards")
    fun getRewards(): Call<List<Reward>>
}
