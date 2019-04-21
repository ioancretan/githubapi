package com.example.githubsample.repos

import android.annotation.SuppressLint
import com.example.githubsample.utils.CredentialsDataSource
import com.example.githubsample.utils.RxLifecycleOwner
import com.example.githubsample.network.GitHubApi
import com.example.githubsample.network.NetworkUtils.Companion.FULL_NAME_SORT
import com.example.githubsample.network.NetworkUtils.Companion.NUMBER_OF_REPOS_PER_PAGE
import com.example.githubsample.network.RetrofitBuilder
import com.example.githubsample.network.RetrofitBuilder.API_BASE_URL
import com.example.githubsample.network.RetrofitBuilder.TOKEN
import com.example.githubsample.network.models.Repo

class ReposPresenter(
    private val repoView: RepoView,
    private val rxLifecycleOwnerActivityV3: RxLifecycleOwner,
    private val api: GitHubApi =
        RetrofitBuilder.getRetrofitInstance().newBuilder().baseUrl(API_BASE_URL).build().create(
            GitHubApi::class.java
        ),
    private val credentialDataSource: CredentialsDataSource = CredentialsDataSource,

    private var currentPAge: Int = 1

) {
    @SuppressLint("CheckResult")
    fun loadMoreRepos() {
        api.getRepos(getTokenRequest(), currentPAge, NUMBER_OF_REPOS_PER_PAGE, FULL_NAME_SORT)
            .compose(rxLifecycleOwnerActivityV3.moveObservableToBackground())
            .subscribe(
                this::onReposLoaded
            ) { throwable -> repoView.onError(throwable.localizedMessage) }
    }

    fun onReposLoaded(repoList: MutableList<Repo>) {

        currentPAge++
        val isLastPage = repoList.size == 0
        if (isLastPage) {
            repoView.setIsLastPage(isLastPage)
        } else {
            repoView.loadReposIntoUi(repoList)
        }
    }

    fun getTokenRequest(): String = TOKEN + credentialDataSource.getAccessToken()

}