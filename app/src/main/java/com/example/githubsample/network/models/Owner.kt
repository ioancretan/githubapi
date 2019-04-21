package com.example.githubsample.network.models

import com.google.gson.annotations.SerializedName

class Owner {

    @SerializedName("avatar_url")
    lateinit var avatarUrl : String
}