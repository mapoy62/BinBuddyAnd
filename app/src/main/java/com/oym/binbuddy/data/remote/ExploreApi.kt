package com.oym.binbuddy.data.remote

import com.oym.binbuddy.data.remote.model.InstagramProfile
import retrofit2.Call
import retrofit2.http.GET

interface ExploreApi {

    @GET("/igprofile")
    fun getInstagramProfiles(): Call<List<InstagramProfile>>
}
