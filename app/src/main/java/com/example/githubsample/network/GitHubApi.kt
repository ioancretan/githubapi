package com.example.githubsample.network

import com.example.githubsample.network.models.AccessToken
import io.reactivex.Observable
import retrofit2.http.*

interface GitHubApi {

    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    fun getAccessToken(@Field("client_id") clientId: String,
                       @Field("client_secret") cleintSecret: String,
                       @Field("code") code: String): Observable<AccessToken>

}