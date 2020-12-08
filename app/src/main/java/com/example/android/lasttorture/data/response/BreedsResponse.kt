package com.example.android.lasttorture.data.response

import com.example.android.lasttorture.data.model.AnimalBreed
import com.google.gson.annotations.SerializedName

data class BreedsResponse (
        @SerializedName("breeds")
        val breedsArray: Array<AnimalBreed>
)