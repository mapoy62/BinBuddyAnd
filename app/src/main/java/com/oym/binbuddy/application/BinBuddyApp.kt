package com.oym.binbuddy.application

import android.app.Application
import com.oym.binbuddy.data.remote.RetrofitHelper
import com.oym.binbuddy.data.repository.ChallengesRepository
import com.oym.binbuddy.data.repository.ComingSoonRepository
import com.oym.binbuddy.data.repository.ExploreRepository
import com.oym.binbuddy.data.repository.RecommendationsRepository
import com.oym.binbuddy.data.repository.RecyclingRepository
import com.oym.binbuddy.data.repository.RewardsRepository
import com.oym.binbuddy.data.repository.TipsRepository
import com.oym.binbuddy.data.repository.TwitterRepository

class BinBuddyApp: Application() {

    //Backend BinBuddyAPI --- Apiary
    private val retrofit by lazy {
        RetrofitHelper().getApiaryRetrofit()
    }

    //SerpAPI
    private val retrofitSerapi by lazy {
        RetrofitHelper().getSerpApiRetrofit()
    }

    //TwitterAPI
    private val twitterRetrofit by lazy {
        RetrofitHelper().getTwitterRetrofit()
    }

    val comingSoonRepository by lazy {
        ComingSoonRepository(retrofit)
    }

    val tipsRepository by lazy {
        TipsRepository(retrofit)
    }

    val challengesRepository by lazy {
        ChallengesRepository(retrofit)
    }

    val recommendationsRepository by  lazy {
        RecommendationsRepository(retrofitSerapi)
    }

    val exploreRepository by lazy {
        ExploreRepository(retrofit)
    }

    // Repositorio de Twitter
    val twitterRepository by lazy {
        TwitterRepository(twitterRetrofit, this)
    }

    val recyclingRepository by lazy {
        RecyclingRepository(retrofit)
    }

    val rewardsRepository by lazy {
        RewardsRepository(retrofit)
    }
}