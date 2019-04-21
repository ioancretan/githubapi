package com.example.githubsample.utils

import com.trello.rxlifecycle2.LifecycleTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer

interface RxLifecycleOwner {

    fun <T> bindToLifecycle(): LifecycleTransformer<T>

    fun <T> moveToBackground(): SingleTransformer<T, T>

    fun <T> moveObservableToBackground(): ObservableTransformer<T, T>

}