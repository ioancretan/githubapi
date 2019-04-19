package com.example.githubsample.login

interface LoginView {
    fun openLoginToGitHubIntent(url: String)
    fun onError(errorMsg: String)
    fun openHomeScreen()
}