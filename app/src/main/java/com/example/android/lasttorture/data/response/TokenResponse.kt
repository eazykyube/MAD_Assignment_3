package com.example.android.lasttorture.data.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("access_token")
    val token: String
)