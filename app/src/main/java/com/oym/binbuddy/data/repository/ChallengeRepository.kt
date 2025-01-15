package com.oym.binbuddy.data.repository

import com.oym.binbuddy.data.remote.ChallengesApi
import com.oym.binbuddy.data.remote.model.Challenge
import retrofit2.Call
import retrofit2.Retrofit


class ChallengesRepository(
    retrofit: Retrofit
) {
    private val api = retrofit.create(ChallengesApi::class.java)

    fun getChallenges(): Call<List<Challenge>> {
        return api.getChallenges()
    }
}