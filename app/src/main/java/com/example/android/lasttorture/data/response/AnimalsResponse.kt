package com.example.android.lasttorture.data.response

import com.example.android.lasttorture.data.model.Animal
import com.google.gson.annotations.SerializedName

data class AnimalsResponse(
        @SerializedName("animals")
        val animalsArray: Array<Animal>
)