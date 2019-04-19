package com.example.githubsample

object CredentialsDataSource {

    const val EMPTY_STRING: String = "EMPTY_STRING"
    private const val ACCESS_TOKEN: String = "ALGO_TOKEN"


    fun getAccessToken(): String {
        return StringSharedPref.getString(ACCESS_TOKEN, EMPTY_STRING)
    }

    fun saveAccessToken(token: String) {
        StringSharedPref.putString(ACCESS_TOKEN, token)
    }

}