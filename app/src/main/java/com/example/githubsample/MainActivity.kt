package com.example.githubsample

import android.arch.lifecycle.ReportFragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.githubsample.login.LoginFragment
import com.example.githubsample.repos.ReposListFragment


class MainActivity : AppCompatActivity(), OpenHomeScreenCallBack {
    override fun openHomeScreen() {
       replaceFragment(ReposListFragment())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(LoginFragment(this))
    }

    protected fun replaceFragment(fragment: android.support.v4.app.Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_content, fragment)
        fragmentTransaction.commit()
    }
}
