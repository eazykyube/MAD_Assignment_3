package com.example.android.lasttorture.data.model

import com.google.gson.annotations.SerializedName

data class AnimalBreed(
    @SerializedName("name", alternate = ["primary"])
    val breed: String
)