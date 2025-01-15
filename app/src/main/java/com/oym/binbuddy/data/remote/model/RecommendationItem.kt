package com.oym.binbuddy.data.remote.model

import com.google.gson.annotations.SerializedName

data class RecommendationItem(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("rating") val rating: Float,
    @SerializedName("author") val author: String,
    @SerializedName("category") val category: String,
    @SerializedName("downloads") val downloads: String,
    @SerializedName("thumbnail") val thumbnail: String
)

