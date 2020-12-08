package com.example.android.lasttorture.data.request

data class TokenRequest(
    val grant_type: String,
    val client_id: String,
    val client_secret: String
)