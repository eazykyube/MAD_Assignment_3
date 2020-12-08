package com.example.android.lasttorture.data

import com.example.android.lasttorture.data.request.TokenRequest
import com.example.android.lasttorture.data.response.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {

    @POST("oauth2/token")
    fun getToken(@Body body: TokenRequest): Call<TokenResponse>

}