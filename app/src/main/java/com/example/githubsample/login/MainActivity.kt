package com.example.githubsample.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.githubsample.R
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
