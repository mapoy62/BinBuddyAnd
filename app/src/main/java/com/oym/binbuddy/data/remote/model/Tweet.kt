package com.oym.binbuddy.data.remote.model

import com.google.gson.annotations.SerializedName

data class Tweet(
    @SerializedName("id") val id: String,
    @SerializedName("author") val author: String,
    @SerializedName("text") val text: String,
    @SerializedName("createdAt") val createdAt: String
)

data class TwitterResponse(
    @SerializedName("data") val tweets: List<Tweet>
)
