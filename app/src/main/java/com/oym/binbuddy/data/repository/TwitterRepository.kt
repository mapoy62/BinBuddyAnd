package com.oym.binbuddy.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oym.binbuddy.data.remote.TwitterApi
import com.oym.binbuddy.data.remote.model.Tweet
import com.oym.binbuddy.data.remote.model.TwitterResponse
import retrofit2.Call
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TwitterRepository(
    private val retrofit: Retrofit,
    private val context: Context
) {
    private val api = retrofit.create(TwitterApi::class.java)
    private val sharedPreferences = context.getSharedPreferences("twitter_cache", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun getCachedTweets(): List<Tweet>? {
        val cachedTweets = sharedPreferences.getString("tweets", null)
        return cachedTweets?.let {
            val type = object : TypeToken<List<Tweet>>() {}.type
            gson.fromJson(it, type)
        }
    }

    fun saveTweetsToCache(tweets: List<Tweet>) {
        val tweetsJson = gson.toJson(tweets)
        sharedPreferences.edit()
            .putString("tweets", tweetsJson)
            .putString("last_fetch_date", getCurrentDate())
            .apply()
    }

    fun getLastFetchDate(): String? {
        return sharedPreferences.getString("last_fetch_date", null)
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }

    fun isDataStale(): Boolean {
        val lastFetchDate = getLastFetchDate()
        val currentDate = getCurrentDate()
        return lastFetchDate == null || lastFetchDate != currentDate
    }

    fun fetchTweets(query: String): Call<TwitterResponse> {
        return api.getRecentTweets(query)
    }
}
