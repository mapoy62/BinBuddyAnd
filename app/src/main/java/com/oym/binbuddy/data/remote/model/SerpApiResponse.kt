package com.oym.binbuddy.data.remote.model

import com.google.gson.annotations.SerializedName

data class SerpApiResponse(
    @SerializedName("organic_results") val organicResults: List<OrganicResult>?
)

data class OrganicResult(
    @SerializedName("items") val items: List<RecommendationItem>
)


