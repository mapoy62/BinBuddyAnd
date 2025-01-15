package com.oym.binbuddy.application

import android.app.Application
import com.oym.binbuddy.data.remote.RetrofitHelper
import com.oym.binbuddy.data.repository.ChallengesRepository
import com.oym.binbuddy.data.repository.ComingSoonRepository
import com.oym.binbuddy.data.repository.RecommendationsRepository
import com.oym.binbuddy.data.repository.TipsRepository

class BinBuddyApp: Application() {

    private val retrofit by lazy {
        RetrofitHelper().getApiaryRetrofit()
    }

    private val retrofitSerapi by lazy {
        RetrofitHelper().getSerpApiRetrofit()
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



}