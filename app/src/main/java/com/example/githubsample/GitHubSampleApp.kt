package com.example.githubsample

import android.app.Application

class GitHubSampleApp : Application() {
    companion object {
        lateinit var gitHubApp: GitHubSampleApp
    }

    override fun onCreate() {
        super.onCreate()
        gitHubApp = this;
    }
}