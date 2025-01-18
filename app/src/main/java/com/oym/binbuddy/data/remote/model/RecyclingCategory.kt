package com.oym.binbuddy.data.remote.model

import com.google.gson.annotations.SerializedName

data class RecyclingCategory(
    @SerializedName("category") val category: String,
    @SerializedName("description") val description: String,
    @SerializedName("recyclingPoints") val recyclingPoints: List<RecyclingPoint>
)

data class RecyclingPoint(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("address") val address: String,
    @SerializedName("hours") val hours: String,
    @SerializedName("acceptedMaterials") val acceptedMaterials: List<String>
)

