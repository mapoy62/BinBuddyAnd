package com.oym.binbuddy.data.remote

import com.oym.binbuddy.data.remote.model.SerpApiResponse
import com.oym.binbuddy.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecommendationsApi {
    @GET("search.json")
    fun getRecommendations(
        @Query("engine") engine: String = "google_play",
        @Query("q") query: String = "environment",
        @Query("api_key") apiKey: String = Constants.SERPAPI_API_KEY
    ): Call<SerpApiResponse>
}
