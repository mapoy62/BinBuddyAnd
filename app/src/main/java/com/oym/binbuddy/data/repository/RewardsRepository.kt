package com.oym.binbuddy.data.repository

import com.oym.binbuddy.data.remote.RewardsApi
import com.oym.binbuddy.data.remote.model.Reward
import retrofit2.Call
import retrofit2.Retrofit

class RewardsRepository(
    retrofit: Retrofit
) {
    private val api = retrofit.create(RewardsApi::class.java)

    fun getRewards(): Call<List<Reward>> {
        return api.getRewards()
    }
}
