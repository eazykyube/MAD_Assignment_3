package com.example.android.lasttorture.data.response

import com.example.android.lasttorture.data.model.AnimalType
import com.google.gson.annotations.SerializedName

data class TypesResponse (
        @SerializedName("types")
        val typesArray: Array<AnimalType>
)