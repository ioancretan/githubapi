package com.example.githubsample.repos

import com.example.githubsample.utils.CredentialsDataSource
import com.example.githubsample.utils.RxLifecycleOwner
import com.example.githubsample.network.GitHubApi
import com.example.githubsample.network.models.Repo
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import org.junit.Before
import org.junit.Test

class RepoPresenterTest {
    private val repoView: RepoView = mock()
    private val api: GitHubApi = mock()
    private val credentialsDataSource: CredentialsDataSource = mock()
    private val lifecycleOwner: RxLifecycleOwner = mock()

    private lateinit var presenter: ReposPresenter

    @Before
    fun setupMocksAndView() {
        whenever(lifecycleOwner.moveObservableToBackground<Any>())
            .thenReturn(ObservableTransformer { upstream -> upstream })


        presenter = ReposPresenter(repoView, lifecycleOwner, api, credentialsDataSource)
    }

    @Test
    fun Should_Call_OnReposLoaded_When_LoadMoreRepos() {

        val presenterSpy = spy(presenter)

        whenever(credentialsDataSource.getAccessToken()).thenReturn("")
        whenever(api.getRepos(any(), any(), any(), any())).thenReturn(Observable.just(mutableListOf()))
        doNothing().`when`(presenterSpy).onReposLoaded(any())

        presenterSpy.loadMoreRepos()

        verify(presenterSpy).onReposLoaded(any())
    }

    @Test
    fun Should_Call_LoadReposIntoUi_When_Repos_List_Size_Is_Greater_than0() {

        val presenterSpy = spy(presenter)

        doNothing().`when`(repoView).setIsLastPage(any())
        doNothing().`when`(repoView).loadReposIntoUi(any())

        val repos = mutableListOf<Repo>()
        repos.add(Repo())

        presenterSpy.onReposLoaded(repos)

        verify(repoView).loadReposIntoUi(repos)
    }

    @Test
    fun Should_Call_SetIsLastPage_When_Repos_List_Size_Is_0() {

        val presenterSpy = spy(presenter)

        doNothing().`when`(repoView).setIsLastPage(any())
        doNothing().`when`(repoView).loadReposIntoUi(any())


        presenterSpy.onReposLoaded(mutableListOf())

        verify(repoView).setIsLastPage(true)
    }

}