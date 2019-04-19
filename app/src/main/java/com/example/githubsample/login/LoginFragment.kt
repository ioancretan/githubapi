package com.example.githubsample.login

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.githubsample.OpenHomeScreenCallBack
import com.example.githubsample.R


@SuppressLint("ValidFragment")
class LoginFragment(val openHomeSCreenCallBack: OpenHomeScreenCallBack) : BaseFragment(), LoginView {
    override fun openHomeScreen() {
        openHomeSCreenCallBack.openHomeScreen()
    }

    private lateinit var loginPresenter: LoginPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loginPresenter = LoginPresenter(this)
        loginPresenter.login()
    }

    override fun onError(errorMsg: String) {
        Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
    }

    override fun openLoginToGitHubIntent(url: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )

        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        loginPresenter.loadAccesToken(activity!!.intent.data)
    }

}