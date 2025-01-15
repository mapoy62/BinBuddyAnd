package com.oym.binbuddy.data.remote.model

import com.google.gson.annotations.SerializedName

data class ComingSoonItem(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("link") val link: String // URL de la imagen
)

