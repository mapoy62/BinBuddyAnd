package com.oym.binbuddy.application

import android.app.Application
import com.oym.binbuddy.data.remote.RetrofitHelper
import com.oym.binbuddy.data.repository.ChallengeRepository

class BinBuddyApp: Application() {

    private val retrofit by lazy {
        RetrofitHelper().getRetrofit()
    }

    val repository by lazy {
        ChallengeRepository(retrofit)
    }
}