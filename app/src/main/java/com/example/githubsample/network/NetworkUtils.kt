package com.example.githubsample.network

import com.example.githubsample.network.RetrofitBuilder.BASE_URL

class NetworkUtils {

    companion object {
        const val CLIENT_ID = "8562134ea57eaa0e2d2f"
        const val CLIENT_SECRET = "2b612f8da5005af2c3777409e4b152c79cf7134e"
        const val REDIRECT_URL = "githubsample://callback"
        const val CODE = "code"
        const val NUMBER_OF_REPOS_PER_PAGE = 2
        const val FULL_NAME_SORT = "full_name"

        fun getLogindUrl() =
            BASE_URL + "/login/oauth/authorize" + "?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL

    }
}