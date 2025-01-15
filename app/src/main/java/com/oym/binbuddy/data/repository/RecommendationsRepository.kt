package com.oym.binbuddy.data.repository

import com.oym.binbuddy.data.remote.RecommendationsApi
import com.oym.binbuddy.data.remote.model.SerpApiResponse
import retrofit2.Call
import retrofit2.Retrofit

class RecommendationsRepository(
    retrofit: Retrofit
) {
    private val api = retrofit.create(RecommendationsApi::class.java)

    fun getRecommendations(apiKey: String): Call<SerpApiResponse> {
        return api.getRecommendations(apiKey = apiKey)
    }
}
