package com.oym.binbuddy.data.remote

import com.oym.binbuddy.data.remote.model.ChallengeDTO
import com.oym.binbuddy.data.remote.model.ChallengeDetailDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ChallengeAPI {

    @GET("challenges")
    fun getChallenges(): Call<MutableList<ChallengeDTO>>

    @GET("challenges/{id}")
    fun getChallengeDetail(
        @Path("id") id: Int?
    ): Call<ChallengeDetailDTO>
}