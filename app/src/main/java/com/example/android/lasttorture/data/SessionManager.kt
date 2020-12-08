package com.example.android.lasttorture.data

import com.example.android.lasttorture.sharedpreferences.SharedPreferencesWrapper

class SessionManager(private val secureSharedPrefs: SharedPreferencesWrapper) {

    companion object {
        const val AUTH_TOKEN = "auth_token"
    }

    fun saveAuthToken(token: String) {
        secureSharedPrefs.set(AUTH_TOKEN, token)
    }

    fun fetchAuthToken(): String? {
        return secureSharedPrefs.getString(AUTH_TOKEN)
    }
}