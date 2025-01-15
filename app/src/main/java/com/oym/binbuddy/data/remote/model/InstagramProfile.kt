package com.oym.binbuddy.data.remote.model

import com.google.gson.annotations.SerializedName

data class InstagramProfile(
    @SerializedName("id") val id: Int,
    @SerializedName("category") val category: String,
    @SerializedName("username") val username: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("urlPage") val urlPage: String
)
