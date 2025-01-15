package com.oym.binbuddy.data.remote

import com.oym.binbuddy.data.remote.model.Challenge
import com.oym.binbuddy.data.remote.model.ChallengeDTO
import com.oym.binbuddy.data.remote.model.ChallengeDetailDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChallengesApi {

    @GET("challenges") // Ajusta si el endpoint es diferente
    fun getChallenges(): Call<List<Challenge>>
}