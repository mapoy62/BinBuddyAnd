package com.oym.binbuddy.data.remote.model

import com.google.gson.annotations.SerializedName

data class Reward(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("rewardType") val rewardType: String,
    @SerializedName("value") val value: Float?,
    @SerializedName("conditions") val conditions: Conditions,
    @SerializedName("category") val category: String,
    @SerializedName("validity") val validity: Validity,
    @SerializedName("isRedeemed") val isRedeemed: Boolean,
    @SerializedName("imageUrl") val imageUrl: String
)

data class Conditions(
    @SerializedName("completedChallenges") val completedChallenges: Int
)

data class Validity(
    @SerializedName("start") val start: String,
    @SerializedName("end") val end: String
)

