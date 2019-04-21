package com.example.githubsample.repos

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.githubsample.R
import com.example.githubsample.utils.RxLifecycleOwnerV3
import com.example.githubsample.login.BaseFragment
import com.example.githubsample.network.models.Repo
import kotlinx.android.synthetic.main.repos_fragment.*

class ReposListFragment : BaseFragment(), RepoView {
    private lateinit var reposAdapter: ReposAdapter
    lateinit var repoPresenter: ReposPresenter
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false


    override fun loadReposIntoUi(repoList: MutableList<Repo>) {
        isLoading = false
        val size = reposAdapter.items.size
        reposAdapter.items.addAll(repoList)
        val sizeNew = reposAdapter.items.size
        reposAdapter.notifyItemRangeChanged(size, sizeNew)
    }

    override fun onError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.repos_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setReposUI()
    }

    private fun setReposUI() {
        repoPresenter = ReposPresenter(this, RxLifecycleOwnerV3(this))
        repoPresenter.loadMoreRepos()
        reposAdapter = ReposAdapter(mutableListOf(), context!!)
        val layoutManager = LinearLayoutManager(this.context)
        repos_recycle_view.layoutManager = layoutManager
        repos_recycle_view.adapter = reposAdapter

        setRecycleViewScrollListener(layoutManager)
    }

    private fun setRecycleViewScrollListener(layoutManager: LinearLayoutManager) {
        repos_recycle_view?.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreRepoItems() {
                isLoading = true
                repoPresenter.loadMoreRepos()
            }
        })
    }

    override fun setIsLastPage(lastPage: Boolean) {
        isLastPage = lastPage
    }
}