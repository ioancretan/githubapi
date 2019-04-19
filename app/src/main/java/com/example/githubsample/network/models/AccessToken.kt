package com.example.githubsample.network.models

import com.google.gson.annotations.SerializedName

class AccessToken {
    @SerializedName("access_token")
    lateinit var accessToken: String

    @SerializedName("token_type")
    lateinit var tokenType: String
}