package com.example.githubsample.network

import com.example.githubsample.RetrofitBuilder.BASE_URL

class NetworkUtils {

    companion object {
        const val CLIENT_ID = "8562134ea57eaa0e2d2f"
        const val CLIENT_SECRET = "2b612f8da5005af2c3777409e4b152c79cf7134e"
        const val REDIRECT_URL = "githubsample://callback"
        const val CODE = "code"

        fun getLogindUrl() =
            BASE_URL + "/login/oauth/authorize" + "?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL
    }
}