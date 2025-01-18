package com.oym.binbuddy.data.remote

import com.oym.binbuddy.data.remote.model.TwitterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitterApi {
    @GET("tweets/search/recent")
    fun getRecentTweets(
        @Query("query") query: String,
        @Query("tweet.fields") fields: String = "author_id,created_at,text"
    ): Call<TwitterResponse>
}