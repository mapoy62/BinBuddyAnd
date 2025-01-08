package com.oym.binbuddy.data.repository

import com.oym.binbuddy.data.remote.ChallengeAPI
import com.oym.binbuddy.data.remote.model.ChallengeDTO
import com.oym.binbuddy.data.remote.model.ChallengeDetailDTO
import retrofit2.Call
import retrofit2.Retrofit

class ChallengeRepository(
    private val retrofit: Retrofit
) {
    private val challengeAPI: ChallengeAPI = retrofit.create(ChallengeAPI::class.java)

    fun getChallenges() : Call<MutableList<ChallengeDTO>> = challengeAPI.getChallenges()

    fun getChallengeDetails(id: Int?): Call<ChallengeDetailDTO> = challengeAPI.getChallengeDetail(id)
}