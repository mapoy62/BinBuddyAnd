package com.oym.binbuddy.data.remote.model

import com.google.gson.annotations.SerializedName

data class Tip(
    @SerializedName("id") val id: Int,
    @SerializedName("content") val content: String,
    @SerializedName("createdAt") val createdAt: String
)
