package com.oym.binbuddy.data.remote.model

import com.google.gson.annotations.SerializedName

data class ChallengeDTO (
    @SerializedName("id")
    var id: Int? = 0,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("description")
    var desc: String? = null,

    @SerializedName("progress")
    var progress: Double? = 0.0,

    @SerializedName("imageUrl")
    var imageUrl: String? = null
)