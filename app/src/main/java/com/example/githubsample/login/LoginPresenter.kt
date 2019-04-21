package com.example.githubsample.login

import android.annotation.SuppressLint
import android.net.Uri
import com.example.githubsample.network.models.AccessToken
import com.example.githubsample.utils.CredentialsDataSource
import com.example.githubsample.utils.CredentialsDataSource.EMPTY_STRING
import com.example.githubsample.network.RetrofitBuilder
import com.example.githubsample.network.GitHubApi
import com.example.githubsample.network.NetworkUtils
import com.example.githubsample.network.NetworkUtils.Companion.CLIENT_ID
import com.example.githubsample.network.NetworkUtils.Companion.CLIENT_SECRET
import com.example.githubsample.network.NetworkUtils.Companion.CODE
import com.example.githubsample.network.NetworkUtils.Companion.REDIRECT_URL
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginPresenter(
    private val loginView: LoginView,
    private val api: GitHubApi = RetrofitBuilder.getRetrofitInstance().create(GitHubApi::class.java)
) {

    @SuppressLint("CheckResult")
    fun login() = if (CredentialsDataSource.getAccessToken() != EMPTY_STRING) {
        loginView.openHomeScreen()
    } else {
        val loginUrl = NetworkUtils.getLogindUrl()
        loginView.openLoginToGitHubIntent(loginUrl)
    }

    @SuppressLint("CheckResult")
    fun loadAccesTokenIfUriExists(uri: Uri?) {

        if (isUriValid(uri)) {
            val code: String = uri!!.getQueryParameter(CODE)

            api.getAccessToken(CLIENT_ID, CLIENT_SECRET, code)
                .subscribeOn(Schedulers.io())
                .map { acessTokenResponse -> getAccesTokenFromResponse(acessTokenResponse) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { acessToken -> onTokenReceived(acessToken) }
                ) { throwable -> this.loginView.onError(throwable.localizedMessage) }
        }
    }

    private fun isUriValid(uri: Uri?) = uri != null && uri.toString().startsWith(REDIRECT_URL)

    private fun getAccesTokenFromResponse(accessToken: AccessToken): String = accessToken.accessToken

    private fun onTokenReceived(accessToken: String) = CredentialsDataSource.saveAccessToken(accessToken)

}