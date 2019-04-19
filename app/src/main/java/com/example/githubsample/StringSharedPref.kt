package com.example.githubsample

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences

object StringSharedPref {

    private val MOTIVATION: String = "Motivation"
    private fun sharedPreferences(): SharedPreferences = GitHubSampleApp.gitHubApp.getSharedPreferences(MOTIVATION, Context.MODE_PRIVATE)


    fun getString(key: String, defaultValue: String): String {
        return sharedPreferences().getString(key, defaultValue)
    }

    fun putString(key: String, value: String) {
        sharedPreferences()
            .edit()
            .putString(key, value)
            .apply()
    }
}