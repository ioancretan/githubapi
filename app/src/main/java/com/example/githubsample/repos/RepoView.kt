package com.example.githubsample.repos

import com.example.githubsample.network.models.Repo

interface RepoView {
    fun loadReposIntoUi(repoList: MutableList<Repo>)
    fun onError(message: String)
    fun setIsLastPage(lastPage: Boolean)
}